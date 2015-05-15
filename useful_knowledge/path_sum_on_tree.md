# Path Sum on Tree

There are several path sum problems on tree. These problems are all more or less related.
We will discuss some of them in this chapter.

Note that tree related problem are quite important. Some of them are not easy to solve
correctly or perfect. Like other discussions of useful knowledge, we will come up with
a rough solution first, and iterate it step by step to product a satisfied one.
Practice makes perfect. And tree related problems are one kind
of problems that need much practice.

Please remember, every time you meet a tree related problem, the first thing is to
make sure that if it is a binary tree. **NEVER** assume the tree is a binary tree yourself!
Ask the interviewee first!

In this chapter, we always assume the trees are binary trees.

## Maximum path sum from root to bottom

One kind of path sum problem is in a case that all path concerned to this problem starts
from the root node. The presence of this condition should be made clear before any further
consideration.

The simplest case of this problem is that the path always start from the root to the leaves.
All value on the nodes is positive is a equivalent condition to the previous.

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
it is possibly end at any node. Often, the value on the tree node may also be negative.
Then there is a question that, if all value on the tree is negative, should we give $$0$$,
stands for empty path, or give the value of root as it will be the maximum sum.

If you are faced with this question, remember that return the value of root is simplier.
We can always return zero when the algorithm returns negative number. So at first we
will come up with a problem that always return a value of node.

```java
int maxPathSum(TreeNode root) {
    if (root == null) return 0;
    int left = maxPathSum(root.left) + root.val;
    int right = maxMathSum(root.right) + root.val;
    int single = root.val;
    return Math.max(single, Math.max(left, right));
}
```

The solution above based on an observation that the max path sum on a node must be
one of 1) the node itself, 2) the node with its left child and the max sum path of the latter,
3) the node with its right child and the max sum path of the latter.

The algorithm above is a bottom to top one, we can also come up with a top to bottom one
with which we can get the end of the maximum sum path at the same time.

```java
class Sum {
    int max;
    TreeNode end;
}

void maxPathSum(TreeNode root, int base, Sum res) {
    if (root == null) return;
    base += root.val;
    if (base > res.max) {
        res.max = base;
        res.end = root;
    }
    maxPathSum(root.left, base, res);
    maxPathSum(root.right, base, res);
}
```

For the former solution, it is a little harder to find a way to return the end of path.
We may try to use a structure as result. The following code shows how to do it.

```java
class Sum {
    int max;
    TreeNode end;
}

Sum maxPathSum(TreeNode root) {
    if (root == null) return new Sum(0, null);
    
    Sum left = maxPathSum(root.left);
    Sum right = maxPathSum(root.right);
    
    left.max += root.val;
    right.max += root.val;
    
    Sum maxsum;
    if (left.max < right.max) maxsum = right;
    else maxsum = left;

    if (root.val > maxsum.max || maxsum.end == null) {
        maxsum.max = root.val;
        maxsum.end = root;
    }
    return maxsum;
}
```

## Path sum from any node to any node

Now we comes to the ultimate problem of this kind, the path we get can start from any node
and end at any node. As it is tree we are talking about, so any path on the tree has the following
properties:

1. The path can be divided into two part with one node
2. The first piece of the path will stretch from that node to its left sub tree
3. The second piece of the path will stretch from that node to its right sub tree
4. Both piece may contains zero all more nodes
5. If the path contains no nodes, then it is not on the tree.

So a rough solution might be that for each node, we find the max root to any node path sum
on its left tree and right tree respectively with algorithms mentioned above
and decide the maximum path sum when the path is through the node.

This solution's time cost is not optimized. We need a better solution that can do this
with just one time of traverse over the nodes. Inspired by the bottom to top algorithm,
a solution using a global variable might be:

```java
class Solution {
    int finalmaxsum;
    
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

What if the start and end nodes of the path is required to be find? It is a little diffculty, isn't it?
But the reality is that you have to have the ability to solve this problem in 20 minutes to get a job
at those top companies.

Let's analyse this problem step by step. At first, the start and end node is obviously swappable,
so we can always regard the left most node as the start and the right most as the end.
The code above has already give us a solution of finding the value of maximum path sum,
what we need to do is to find a we to keep the end of maxium single path sum. We will
do this by combinating the global variable version with the `Sum` structure.

```java
class Solution {
    int finalmaxsum;
    TreeNode start;
    TreeNode end;

    static class Sum {
        int max;
        TreeNode end;
    }
    
    Sum maxSingleSum(TreeNode root) {
        if (root == null) return new Sum(0, null);
        
        Sum left = maxSingleSum(root.left);
        Sum right = maxSingleSum(root.right);
        
        left.max += root.val;
        right.max += root.val;
        int throughmax = left.max + right.max - root.val;
        int single = root.val;
        
        Sum maxsum;
        int submax;
        TreeNode substart;
        TreeNode subend;
        
        if (left.max < right.max) {
            maxsum = right;
            submax = right.max;
            substart = root;
            subend = right.end;
        } else {
            maxsum = left;
            submax = left.max;
            substart = left.end;
            subend = root;
        }
        
        if (maxsum.max < single || maxsum.end == null) {
            maxsum.max = single;
            submax = single;
            maxsum.end = root;
            substart = root;
            subend = root;
        }

        if (through > maxsum.max) {
            submax = through;
            substart = left.end;
            subend = right.end;
        }

        if (submax > finalmaxsum) {
            finalmaxsum = submax;
            start = substart;
            end = subend;
            if (start == null) start = root;
            if (end == null) end = root;
        }
        return maxsum;
    }

    int maxPathSum(TreeNode root) {
        finalmaxsum = Integer.MIN_VALUE;
        maxSingleSum(root);
        return finalmaxsum;
    }
}
```

This problem is usually harder than average, but it is not a excuse to give it up.
In fact, it is a real problem used to be presented during an interview.

You will definitely pass an interview if you have a goal higher than it
and keep working on!
