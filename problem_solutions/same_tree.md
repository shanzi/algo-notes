# Same Tree

Chack if two tree is the same. This is a simple problem, but please note that in the code we
are using `^` operator to check if two boolean expression is not equal.

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null ^ q == null) return false;
        
        if (p.val != q.val) return false;
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

`p == null ^ q == null` can also be replaced with `(p == null) != (q == null)`.
