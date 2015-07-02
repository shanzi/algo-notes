# Count Complete Tree Nodes

Complete tree is a binary tree that every level except possibly the last if completed full filled,
and all nodes in the last level is as far left as possble. It is not necessary to visit each nodes to count
the number of nodes as many of subtrees of a complete tree is full or perfect binary tree.
For more information on complete tree and perfect tree please refer to
[wiki](https://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees).

A perfect tree of depth $$h$$ contains $$2^h - 1$$ nodes. To count complete tree nodes, we at first
verify if a sub-tree is a perfect tree. If it is, we directly return the nodes count of this sub-tree.
If it is not a perfect tree, we recursely do the same thing at both two child node and return
the sum plus 1 as result.

To verify if a tree is a perfect tree, we respectively traverse left child and right child all the way
and count the left-most depth and right-most depth. If a tree is a perfect binary tree, left-most
depth will be the same as right-most depth.

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        
        TreeNode p = root, q = root;
        
        int leftHeight = 0;
        int rightHeight = 0;
        
        while (p != null) {
            p = p.left;
            leftHeight++;
        }
        
        while (q != null) {
            q = q.right;
            rightHeight++;
        }
        
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
}
```
