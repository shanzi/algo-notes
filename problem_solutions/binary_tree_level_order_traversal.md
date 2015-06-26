# Binary Tree Level Order Traversal.

BFS traverse on the tree.

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
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        TreeNode last = root;
        TreeNode nlast = null;
        
        ArrayList<Integer> row = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            row.add(node.val);
            
            if (node.left != null) {
                queue.add(node.left);
                nlast = node.left;
            }
            
            if (node.right != null) {
                queue.add(node.right);
                nlast = node.right;
            }
            
            if (node == last) {
                res.addLast(row);
                row = new ArrayList<Integer>();
                last = nlast;
            }
        }
        return res;
    }
}
```

If a reversed list is required, replace `res.addLast(row)` with `res.addFirst(row)`.
