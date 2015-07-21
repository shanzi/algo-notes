# Edit Distance

Edit distance is a classic alogrithm, it uses dynamic programming method.
Let $$DP[i][j]$$ to be the minimum edit distance to modify `str1[0..i]` to `str2[0..j]`.
Then we will have four iteration case in this problem:

1. If we insert a character, $$DP[i][j] = DP[i][j - 1] + 1$$
2. If we delete a character, $$DP[i][j] = DP[i - 1][j] + 1$$
3. If we replace a character, $$DP[i][j] = DP[i - 1][j - 1] + 1$$
4. If the next character is the same, $$DP[i][j] = DP[i - 1][j - 1]$$

We pick the minimum $$DP[i][j]$$ of the four cases at last. The final result
will be $$DP[str1.length][str2.length]$$.

```java
public class EditDistance {
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        
        int[][] dp = new int[w1.length + 1][w2.length + 1];
        
        for (int i = 0; i <= w1.length; i++) {
            for (int j = 0; j <= w2.length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + 1;
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + 1;
                } else {
                    int replace = (w1[i - 1] == w2[j - 1] ? 0 : 1);
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + replace, Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
                }
            }
        }
        
        return dp[w1.length][w2.length];
    }
}
```
