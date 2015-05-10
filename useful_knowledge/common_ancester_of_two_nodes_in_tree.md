# Common Ancester of Tree

Common Ancester of Tree is said to be a very common problem asked during an interview.
This problem is not a hard one, but there are still several points should be carefully
handled. And the most important notice to remember is that, **DO NOT** assume the tree
is a binary tree. Repeat, **DO NOT** assume the tree is a binary tree.

Yes, many will fall at the first step. Didn't make it clear when answering a question
is the one of the mistakes that should not be made most. So the first step to solve
this problem should always be making sure if the tree in the question is a binary tree.

Then, if you are sure it is about binary tree, then the following is related notes.

## Binary Tree

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

```
TreeNode findCommonAncester(TreeNode root, TreeNode a, TreeNode b) {
    if (root == null) return null;
    if (root == a || root == b) return root;
    
    TreeNode left = findCommonAncester(root.left, a, b);
    TreeNode right = findCommonAncester(root.right, a, b);
    
    if (left != null && right != null) return root;
    else if (left == null) return right;
    else return left;
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


