# Balanced Binary Tree

Check whether a binary tree is balanced(difference of depths for each node's two subtree is no more than 1).
Using recursion method and return `-1` if find a subtree is not balanced.

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

public class BalancedBinaryTree {
    
    private int checkBalanced(TreeNode root) {
        if (root == null) return 0;
        
        int left = checkBalanced(root.left);
        int right = checkBalanced(root.right);
        
        if (left < 0 || right < 0) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
    
    public boolean isBalanced(TreeNode root) {
        return (checkBalanced(root) >= 0);
    }
}
```
