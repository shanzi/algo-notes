# Balanced Binary Search Trees

Here we take notes of balanced binary search trees. To be more specific, we will
put more details in the so-called left leaning red-black tree.

Usually, a red-black tree is thought to be extremely hard to implement. There are even
a famous lawsuit concerns about it. Here we do not talk about the traditional red-black tree
which is invented in the 1980s, but we are going to talk about a newly invented variant of it
which is quite easy to implement and use.

To beginning, we first introduce the preliminary binary search tree which is not guaranteed to be balanced.
Below are a skeleton of functions such BST should have(to be simpler, we just use `int` as data type:

```java
class Node {
    Node left = null;
    Node right = null;
	Integer key;
	Integer val;
    public Node(Integer key, Integer val) {
        this.key = key;
        this.val = val;
    }
}
public class BST {
    Node root = null;
    public Integer get(Integer key) {
        return get(root, key);
    }
    public void put(Integer key, Integer val) {
        root = put(root, key, val);
    }
    private Integer get(Node h, Integer key) {
        if (h == null) return null;
        else if (key < h.key) return get(h.left, key);
        else if (key > h.key) return get(h.right, key);
        else return h.val;
    }
    private Node put(Node h, Integer key, Integer val) {
        if (h == null) return new Node(key, val);
        else if (key < h.key) h.left = put(h.left, key, val);
        else if (key > h.key) h.right = put(h.right, key, val);
        else h.val = val;
        return h;
    }
}
```

Note in this implementation, we do not add codes for deletion which we will discuss later.
And when we put new data, we always set pointer to left or right subtree and root to the results
of `put(Node root, Integer key, Integer val)` function to make the implementation more clear and simpler.

Current implementation's defects are obvious. The search tree may become to deep or even degenerate to
a chain which lead to linear searching and inserting time cost. Also it is volnerable to `StackOverFlowError`.
To solve this question. we will introduce red-black tree.

AAccording to Wikipedia, a red-black tree is a binary search tree that has an extra bit for its color
which can be black or red. It has the following properties:

1. The root is black
2. All leaves (which is `null`) are all black
3. If a node is red, both of its children are black
4. Any path from any node to its descendant `null` leaves has the same number of black nodes

From the fourth property we know that the time cost to search the tree at most cost $$O(\log N)$$ time
where $$N$$ is the number of total elements in the tree. Let's assume there are $$B$$ black nodes from
root to leaves, then there are at most $$B - 1$$ red nodes in a path from root to leaves. So there
are at most $$B\times 2 - 1$$ nodes in total in a path from root to leaves. Also the shortest path has
$$B$$ nodes. As every path has $$B$$ black nodes, we can induct that $$B$$ is at most $$\log N$$,
thus the longest path from root to leaves is at most $$2\log N - 1$$ which is $$O(\log N)$$.

To correctly implement the original red-black tree is extremely hard. But fortunately, one of the authors
of red-black tree himself invented a new kind of red-black tree which is easy to code. It is called left leaning
red-black tree. This variant is invented at 2008.

The idea of left leaning red-black tree is quite simple: we just forbid red nodes to exist as the right child
of any black node. In other words, red nodes can only appear as left child now. This limit reduced cases that
need to handle when inserting or deleting a node.

The search operation is the same as the original, the problem exists in the insertion. At first, we
let all new inserted node to be red node, then as to keep the red-black tree's left leaning balanced property,
 we have to do some extra operations. There are several different cases:

1. we have a red node as right child, which violate the left leaning property. In this case, we need to perform
a rotate left operation.
2. we have two connected red nodes, that is we have a node that both its left and left of left child are red.
At this time, we have to perform a rotate right operation.
3. we have a node whose both left and right child are red. Such node may comes from a right rotation from the
second case or new node inserting. As for this case, we need flip colors of two child nodes and current node.

We update the node definition and add a new function named `isRed` to check a node's color as we want `null`
node to be black. The rotate left, rotate right and flip color operations are easy to implement too.

```java
class Node {
    Node left = null;
    Node right = null;
	Integer key;
	Integer val;
    boolean isRed = true;
    public Node(Integer key, Integer val) {
        this.key = key;
        this.val = val;
    }
}
public class RedBlackBST {
    private boolean isRed(Node n) {
        return !(n == null) && n.isRed;
    }
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.isRed = h.isRed;
        h.isRed = true;
        return x;
    }
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.isRed = h.isRed;
        h.isRed = true;
        return x;
    }
    private void flipColor(Node h) {
        h.isRed = true;
        h.left.isRed = false;
        h.right.isRed = false;
    }
    /* ... omitted ... */
}
```

As you see, to get a more simpler implementation of put operation, we let `rotateLeft` and `rotateRight`
function returns the root of subtree after rotation, so we can modify the original put function as:

```java
public class RedBlackBST {
    /* ... omitted ... */

    public void put(Integer key, Integer val) {
        root = put(root, key, val);
        root.isRed = false;
    }
    private Node put(Node h, Integer key, Integer val) {
        if (h == null) return new Node(key, val);
        else if (key < h.key) h.left = put(h.left, key, val);
        else if (key > h.key) h.right = put(h.right, key, val);
        else h.val = val;

        // Adjust colors and structures
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColor(h);

        return h;
    }
}
```

Note that we need to keep `root` of the whole tree to be black all the time. So after each put we always set
`root.isRed` to false.

As to compare left leaning red black tree to preliminary binary search tree, we test by sequentially inserting
1000000 values. The preliminary binary search tree just raised a `StackOverflowError` while our red-black tree
runs successfully in about `5.76` seconds. After reduce the key range from 1000000 to 10000, the preliminary
implementation ran for about `1.24` seconds while red black tree finished in `0.12` seconds. The time cost
is reduced quite a lot.
