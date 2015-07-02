# Kth Smallest Element in a BST

BST is a sorted tree, in which each node's left child holds a value smaller and right child holds
a value larger. If we traverse the BST in in-order, we will traverse the values from smallest to
the largest. So to find the $$K$$th smallest element in a BST, we just perform in-order traversal
and pick the $$K$$th element we visit.

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
public class KthSmallestElementInABST {
    public int kthSmallest(TreeNode root, int k) {
        int count = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        
        while (!stack.isEmpty()) {
            p = stack.pop();
            if (++count == k) return p.val;
            
            p = p.right;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }
        
        return p.val;
    }
}
```
