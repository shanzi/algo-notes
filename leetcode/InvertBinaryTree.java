/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        TreeNode p = root;
        TreeNode q = null, r = null;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        
        while (!stack.isEmpty()) {
            p = stack.pop();
            q = p.right;
            while (q != null) {
                stack.push(q);
                q = q.left;
            }
            r = p.right;
            p.right = p.left;
            p.left = r;
        }
        
        return root;
    }
}
