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

## Delete a key

Delete a key is a little complex even in preliminary binary search tree. We can first think about
the way to delete the node with minimum or maximum key of subtree. We also use recursive way here.
To find the lowest key, we follow on pointers to left child until we meet a node whose left node
is `null`, then we remove that node. Following code shows how to do it:

```java
private Node deleteMin(Node x) {
    if (x.left == null) return x.right;
    x.left = deleteMin(x.left);
    return x;
}
```

The implementation of `deleteMax` is similar. Now we make use of this method to enables removing
a node whose both two children are not null. The idea is simple. We replace the node with the node
with minimum key in its right subtree and then use `deleteMin` to delete that min node.(Or we replace
it with the node with maximum key in its left subtree)

To achieve this, we may need another function to get the node with minimum key first. Here we use
loop instead of recursion to do this.

```java
private Node min(Node x) {
    while (x.left != null) {
        x = x.left;
    }
    return x;
}
```

Then, along with deletion method of nodes which either left or right node is null, we can provide the
delete function:

```java
private Node delete(Node x, Integer key) {
    if (x == null) return null;
    if (key < x.key) delete(x.left, key);
    else if (key > x.key) delete(x.right, key);
    else {
        if (x.left == null) return x.right;
        else if (x.right == null) return x.left;

        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
    }
    return x;
}
```

The deletion in red-black tree is in similar idea but is more complex as we need to keep the balance
of tree and subtrees while recursively deletion. For the details of this aspect please refer to
the book *Algorithm*.

## Select and Rank

In any binary search tree, if we keep the size of subtree in each node, we will be going to perform
select and rank operation fast. The select operation returns the kth least key in the tree and
rank operation returns a key's rank in the tree.

The implememtation is like:

```java
private int size(Node x) {
    if (x == null) return 0;
    else return x.size;
}
public Integer select(int k) {
    Node x = select(root, k);
    if (x != null) return x.key;
    else return x;
}
private Node select(Node x, int k) {
    // k starts from zero
    if (x == null) return null;
    int t = size(x.left);
    if (t > k) return select(x.left, k);
    else (t < k) return select(x.right, k - t - 1);
    else return x;
}
```

and,

```java
public int rank(int key) {
    return rank(root, key);
}
private int rank(Node x, int key) {
    if (x == null) return 0;
    if (key < x.key) return rank(x.left, key);
    else if (key > x.key) return size(x.left) + 1 + rank(x.right, key);
    else return size(x.left);
}
```

## Range query

We can also perform range query on the tree. For example, if we'd like to return keys between `lo` and `hi` inclusive,
we can use the following code:

```java
public Iterable<integer> keys(Integer lo, Integer hi) {
    Queue<Integer> queue = new Queue<Integer>();
    keys(root, queue, lo, hi);
    return queue;
}
private void keys(Node x, Queue<Integer> queue, Integer lo, Integer hi) {
    if (x == null) return;
    if (x.key > lo) keys(x.left, queue, lo, hi);
    if (x.key >= lo && x.key <= hi) queue.enqueue(x.key);
    if (x.key < hi) keys(x.right, queue, lo, hi);
}
```
