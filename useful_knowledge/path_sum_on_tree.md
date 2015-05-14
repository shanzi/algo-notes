# Path Sum on Tree

There are several path sum problems on tree. These problems are all more or less related.
We will discuss some of them in this chapter.

Note that tree related problem are quite important. Some of them are not easily to solve
correctly or perfect. Like other discussions of useful knowledge, we will come up with
a rough solution first, and iterate it step by step to product a satisfied one.

Please remember that practice makes perfect. And tree related problems are one kind
of problems that need much practice.

And also remember, every time you meet a tree related problem, the first thing is to
make sure that if it is a binary tree. **NEVER** assume the tree is a binary tree yourself!
Ask the interviewee first! As for our discussion, we always assume the trees are binary trees.

## Maximum path sum from root to bottom

One kind of path sum problem is in a case that all path concerned to this problem starts
from the root node. The presence of this condiion should be made clear before any further
consideration.

The simplest case of this problem is that the path always start from the root to the leaves,
or all value in the node is positive which is a equivalent condition to the previous.

We just cost $$O(N)$$ to traverse the tree once and get the max path sum at the root node.
$$N$$ stands for number of nodes.

```java
int maxPathSum(TreeNode root) {
    if (root == null) return 0;
    return root.val + Math.max(
        maxPathSum(root.left),
        maxPathSum(root.right)
    )
}
```

An variant is that a path must start from root but no need to end at a leaf. In contrast,
it is possibly end at any node. Usually, the value on the tree node may also be negative.
Then there is a question that, if all value on the tree is negative, should we give $$0$$,
stands for empty path, or give the value of root as it will be the maximum sum.

Assume we should return $$0$$. The solution will be:

```java
int maxPathSum(TreeNode root, int base) {
    if (root == null) return base;
    int newbase = base + root.value;
    int left = maxPathSum(root.left, newbase);
    int right = maxPathSum(root.right, newbase);
    return Math.max(base, left, right);
}
```

At first we call the function with `maxPathSum(root, 0)` and it will return the maxium sum
from `root` to any node. A more straight forward but verbose solution is that we define a
structure to kind of enable Java to change the value of parameter.

```java
class Sum {
    int max,
}

void maxPathSum(TreeNode root, int base, Sum res) {
    if (root == null) return;
    base += root.value;
    if (base > res.max) res.max = base;
    maxPathSum(root.left, base, res);
    maxPathSum(root.right, base, res);
}

int maxPathSum(TreeNode root) {
    Sum res = new Sum(0);
    maxPathSum(root, 0, res);
    return res.max;
}
```

Both two solutions above caluate from top to bottom, what if we calculate it from bottom up?
It is obviously that, as for a node, the path with max sum must be one of the following cases:

1. The node itself alone is the path with max sum
2. The node with its left child combines the path with max sum from its left child
3. The node with its right child combines the path with max sum from its right child

So, we get might be the best solution for this problem:

```java
int maxPathSum(TreeNode root) {
    if (root == null) return 0;
    int left = maxPathSum(root.left) + root.val;
    int right = maxPathSum(root.right) + root.val;
    return Math.max(Math.max(left, right), root.val);
}
```

A more complicated problem might demand you to return the
end node of the path. Based on the second solution above, we can give the answer
easily.

```java
class Sum {
    int max;
    TreeNode end;
}

void maxPathSum(TreeNode root, int base, Sum res) {
    if (root == null) return;
    base += root.value;
    if (base > res.max) {
        res.max = base;
        res.end = root;
    }
    maxPathSum(root.left, base, res);
    maxPathSum(root.right, base, res);
}
```

## Path sum from any node to any node

Now we comes to the ultimate problem of similar kind, the path we get can start from any node
and end at any node. As it is tree we are talk about, so any path on the tree has the following
properties:

1. The path can be divided into two part with one node
2. The first piece of the path will stretch from that node to its left sub tree
3. The second piece of the path will stretch from that node to its right sub tree
4. Both piece may contains zero all more nodes
5. If the path contains no nodes, then it is not on the tree.

So a rough solution might be that for each node, we find the max root to any node path sum
on its left tree and right tree respectively decide the max path sum if the path is through
this node.

This solution's time cost is not optimized. We need a better solution that can do all of this
with just one time of traverse over the nodes.

Using a global variable, a solution might be:

```java
class Solution {
    int finalmaxsum = 0;
    
    int max(int... numbers) {
        int m = Integer.MIN_VALUE;
        for (Integer i : numbers) {
            if (i > m) m = i;
        }
        return m;
    }

    int maxSingleSum(TreeNode root) {
        if (root == null) return 0;
        
        int left = maxSingleSum(root.left) + root.val;
        int right = maxSingleSum(root.right) + root.val;
        int through = left + right - root.val;
        int selfalone = root.val;

        int maxsum = max(left, right, through, selfalone);
        if (maxsum > finalmaxsum) finalmaxsum = maxsum;
        
        return max(left, right, selfalone);
    }

    int maxPathSum(TreeNode root) {
        finalmaxsum = Integer.MIN_VALUE;
        maxSingleSum(root);
        return finalmaxsum;
    }
}
```
