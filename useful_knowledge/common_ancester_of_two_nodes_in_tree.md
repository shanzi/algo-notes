# Common Ancester of Two Nodes in Tree

Common Ancester of Tree is said to be a very common problem asked during an interview.
This problem is not a hard one, but there are still several points should be carefully
handled. And the most important notice to remember is that, **DO NOT** assume the tree
is a binary tree. Repeat, **DO NOT** assume the tree is a binary tree.

Yes, many will fall at the first step. Didn't make it clear when answering a question
is the one of the mistakes that should not be made most. So the first step to solve
this problem should always be making sure if the tree in the question is a binary tree.

Then, if you are sure it is about binary tree, then the following is related notes.

## Binary tree

The tree to find common ancester in is a binary tree, and let's say we have two nodes and
these two nodes are guaranteed exist in the tree (This is one point that you should make
it certain before answering).

The second step should always be settle the data structures down. It is also important too.
Will will using the a `TreeNode` like:

```java
class TreeNode {
    TreeNode left;
    TreeNode right;
    int value;
}
```

A good enough solution is to traverse through every nodes
exactly once recursively and find a node follows the conditions below or vice versa:

1. the node A is in the left sub tree of this node
2. the node B is in the right sub tree of this node

Note, both A and B might be the common ancester too. So a simple program to solve the
problem might be:

```java
TreeNode findCommonAncester(TreeNode root, TreeNode a, TreeNode b) {
    if (root == null) return null;
    if (root == a || root == b) return root;
    
    TreeNode left = findCommonAncester(root.left, a, b);
    TreeNode right = findCommonAncester(root.right, a, b);
    
    if (left != null && right != null) return root;
    else if (left != null) return left;
    else return right;
}
```

There is a problem with the code above that if one of a or b is not in the tree and
the function returns a or b, we can not make sure if the node is really the common
ancester(and the other node is in the children tree of this node) or the other node
is not in the tree.

This problem is not easy to solve under Java which can not return multiple return value.
A possible solution might be check the tree and make sure they are all exist in the tree
before running the algorithm above. It keeps the same time complexity for algorithm analysis
but might be much slower in practical.

The following code uses a structure as the return value of the function to get the
common ancester of two nodes while nodes might not be in the tree.

```java
class Res{
    TreeNode a = null;
    TreeNode b = null;
    TreeNode ancester = null;
}

Res findCommonAncester(TreeNode root, TreeNode a, TreeNode b) {
    if (root == null) return new Res();
    
    Res left = findCommonAncester(root.left, a, b);
    Res right = findCommonAncester(root.right, a, b);

    if (left.ancester != null) return left;
    if (right.ancester != null) return right;

    if (right.a != null) left.a = right.a;
    if (right.b != null) left.b = right.b;

    if (root == a) left.a = root;
    if (root == b) left.b = root;
    
    if (left.a != null && left.b != null) {
        left.ancester = root;
    }

    return left;
}

TreeNode findCommonAncester(TreeNode root, TreeNode a, TreeNode b) {
    Res res = findCommonAncester(root, a, b);
    return res.ancester;
}
```

Let's say the tree has $$N$$ nodes. The time cost of the algorithm is $$O(N)$$ as in the worst case,
every node will be visited once. As them use recursion. The average space cost is $$O(\log N)$$.
But in the worst case, the space complexity may be $$O(N)$$ and if $$N$$ is very large, it will
stack overflow.

## Multiple children

If the tree is not a binary tree, the problem must be upgrade. Here we only discuss
the case that both two nodes to find ancester are exist in the tree. At first we
must change the data structure of the TreeNode.

```java
class TreeNode {
    List<TreeNode> children;
    int value;
}
```

The program to find the common ancester doesn't change a lot from the binary tree's program.
A node is the first common ancester if and only if the two node are in different child tree of this node.

```java
TreeNode findCommonAncester(TreeNode root, TreeNode a, TreeNode b) {
    if (root == null) return null;
    if (root == a || root == b) return root;

    TreeNode finda = null;
    TreeNode findb = null;
    for (TreeNode n : root.children) {
        if (n == a) finda = a;
        else if (n == b) findb = b;
    }
    if (finda != null && findb != null) return root;
    else if (finda != null) return finda;
    else return findb;
}
```

The time complexity is also $$O(N)$$. The worst space cost is $$O(N)$$, so it is susceptible
to stack overflow error too.

## Node has pointer to parent

If the Node has a pointer to parent or we can easily get the path from root to the two nodes,
there is another way to find the most common ancester. At first we get two path from root
to node a and b. Then we check from the root one by one parallel until at some position,
two nodes are different. The first common ancester stands exactly one step before that position.

We implemented this using a `LinkedList` in java:

```java
TreeNode {
    //...
    TreeNode parent; // the pointer to the parent
}

TreeNode findCommonAncester(TreeNode a, TreeNode b) {
    LinkedList<TreeNode> apath = new LinkedList<TreeNode>();
    LinkedList<TreeNode> bpath = new LinkedList<TreeNode>();
    while (a != null) {
        apath.addFirst(a);
        a = a.parent;
    }
    while (b != null) {
        bpath.addFirst(b);
        b = b.parent;
    }
    TreeNode node = null;
    while ((!apath.isEmpty()) && (!bpath.isEmpty())) {
        TreeNode m = apath.pollFirst();
        TreeNode n = bpath.pollFirst();
        if (m == n) node = m;
        else break;
    }
    return node;
}
```

The program above should be able to handle both binary tree or tree has multiple children, not only
when both two nodes present in the tree, but also when one or more are not exist.

This problem is usually better than the older one when the parent pointer presents. We can also
get all ancesters easily. The time complexity is $$O(\log N)$$ where $$N$$ is the number of nodes.
Compared the recursive solution, this solution is obviously faster and not vulnerable to stack
overflow error. But it requires the `TreeNode` has an extra field. If this field is originally exist,
that is good, or it might not be suitable. The space complexity is $$O(\log N)$$.

Suggestions when faced with this problem in an interview:

1. if the `TreeNode` can be self defined. Just use the last algorithm. It has many advantages
and does not easily to be wrong.
2. if `TreeNode` is given and no parent pointer allowed, remember to mention this solution apart
from the recursive one. What's more, gives the shortcomings of the recursive solutions and
in what way this algorithm is better.
