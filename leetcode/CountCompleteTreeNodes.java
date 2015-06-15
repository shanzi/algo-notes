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
