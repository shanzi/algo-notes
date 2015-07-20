# Recover Binary Search Tree

The items in a binary search tree are distinct and sorted in ascending order with in-order traversal.
So, if two elements a exchanged, there will be two exception in the ascending order. Those two items
are what we want to find.

Note the first exception will be a node has higher value than is descender and the section
exception will be a node has lower value than its ascender. These two exception may be adjoined
items in the in-order traversal.

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

class Res {
    int next = Integer.MIN_VALUE;
    TreeNode a;
    TreeNode b;
} 

public class RecoverBinarySearchTree {
    private void findWrongNodes(TreeNode root, Res res) {
        if (root == null) return;
        
        findWrongNodes(root.left, res);
        if (res.next > root.val) {
            res.b = root;
        }
        
        res.next = root.val;
        if (res.b == null) res.a = root;
        
        findWrongNodes(root.right, res);
    }
    
    public void recoverTree(TreeNode root) {
        Res res = new Res();
        findWrongNodes(root, res);
        
        if (res.a != null && res.b != null) {
            int val = res.a.val;
            res.a.val = res.b.val;
            res.b.val = val;
        }
    }
}
```
