# Symmetric Tree

Apply BFS on the tree. Every time we pick a pair of nodes from the queue and compare them.
When adding new nodes to the queue, we always add in pairs that those at the symmetric position
(and thus should have same value) togather. And we put everything include `null` as placeholder
and check equality when polling from the queue.

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
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        
        queue.addLast(root.left);
        queue.addLast(root.right);
        
        while (!queue.isEmpty()) {
            TreeNode a = queue.pollFirst();
            TreeNode b = queue.pollFirst();
            
            if (a == null && b == null) continue;
            else if (a == null ^ b == null) return false;
            
            if (a.val != b.val) return false; 
            
            queue.addLast(a.left);
            queue.addLast(b.right);
            queue.addLast(a.right);
            queue.addLast(b.left);
        }
        
        return true;
    }
}
```
