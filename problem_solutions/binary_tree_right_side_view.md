# Binary Tree Right Side View

There are several ways to solve this problem. For example, using BST to traverse
over all the nodes in the tree and preserve only the last node in each iteration level.
Another way is using in-order traverse and give a number to each level of nodes and let
the nodes that are visited later override that visited earlier at the corresponding position.
The code below shows how to do this:

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
public class BinaryTreeRightSideView {
    private void traverseTree(TreeNode root, int level, List<Integer> res) {
        if (root == null) return;
        if (level >= res.size()) {
            res.add(root.val);
        } else {
            res.set(level, root.val);
        }
        traverseTree(root.left, level + 1, res);
        traverseTree(root.right, level + 1, res);
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        traverseTree(root, 0, res);
        return res;
    }
}
```
