# Unique Binary Search Trees

Give the number of nodes $$n$$, return the number of different binary search trees possible.
As in binary search tree, values of in-order traversal are aways increasing. So the tree labeling
is unique once the structure of binary search tree is given.

We can use a dynamic programing algorithm to solve this problem. Let $$DP[n]$$ denotes count of 
binary search trees that have $$n$$ nodes, then we have:

{% math %}
DP[n] = \sum^{n - 1}_{k = 0} DP[k]\times DP[n - k - 1]
{% endmath %}

```java
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        
        return dp[n];
    }
}
```

If all possible trees is asked to be returned. We use recursively ways to solve the problems.
We right a general function to generate all possible binary search trees of every elements in
range `l` and `r` inclusively and trigger the function with `l = 1` and `r = n`.

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
public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int l, int r) {
        ArrayList<TreeNode> res = new ArrayList<TreeNode>();
        
        if (l > r) {
            res.add(null);
            return res;
        }
        
        for (int i = l; i <= r; i++) {
            List<TreeNode> leftTrees = generateTrees(l, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, r);
            
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        
        return res;
    }
    
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }
}
```

