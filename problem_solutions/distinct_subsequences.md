# Distinct Subsequences

Apply dynamic programing method. For string `S` and `T`, let `DP[i][j]` stands for
the number of ways for `T[0..i]` to be subsequences of `S[0..j]`. We can easily give
the iteration formular:

{% math %}
DP[i][j]=\begin{cases}
DP[i-1][j-1]+DP[i][j-1] & S[i]=T[j]\\
DP[i][j-1] & otherwise
\end{cases}
{% endmath %}

After compressed the space cost, we can come up with a solution like:

```java
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        if (t.isEmpty()) return 1;
        if (s.length() < t.length()) return 0;
        if (s.length() == t.length()) return s.equals(t) ? 1 : 0;
        
        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();
        
        int[] dp = new int[tchar.length + 1];
        
        dp[0] = 1;
        
        for (int j = 1; j <= schar.length; j++) {
            char ch = schar[j - 1];
            
            for (int i = tchar.length; i > 0;i--) {
                if (tchar[i - 1] == ch) {
                    dp[i] += dp[i - 1];
                }
            }
        }
        
        return dp[tchar.length];
    }
}
```
