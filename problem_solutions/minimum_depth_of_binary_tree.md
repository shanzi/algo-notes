# Minimum Depth of Binary Tree

Find the shortest path length from root to any of the leaves. A simple BFS problem.
Note that using DFS in this problem is vulnerable to `StackOverflowError`.
We are using a `LinkedList` as quene and using two pointer to mark end of a level.

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
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        TreeNode last = root;
        TreeNode nlast = null;
        
        int depth = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            
            if (node.left != null) {
                queue.addLast(node.left);
                nlast = node.left;
            }
            
            if (node.right != null) {
                queue.addLast(node.right);
                nlast = node.right;
            }
            
            if (node.left == null && node.right == null) return depth;
            
            if (node == last) {
                last = nlast;
                depth++;
            }
        }
        return depth;
    }
}
```

The code below uses DFS. It also get an AC on LeetCode.

```java
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        if (root.left != null && root.right != null) {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        } else if (root.left != null) {
            return minDepth(root.left) + 1;
        } else {
            return minDepth(root.right) + 1;
        }
    }
}
```
