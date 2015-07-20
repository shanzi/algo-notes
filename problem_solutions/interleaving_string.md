# Interleaving String

Apply dynamic programming method. For `s1`, `s2` and `s3`, let $$DP[i][j]$$ be if
`s1[0..i]` and `s2[0..j]` can interleave `s3[0..(i+j)]`. So the iteration formular will be:

{% math %}
DP[i][j]=\begin{cases}
DP[i][j]\, or\, DP[i-1][j] & s_{1}[i-1]=s_{3}[i+j-1]\\
DP[i][j]\, or\, DP[i][j-1] & s_{2}[i-1]=s_{3}[i+j-1]\\
false & otherwise
\end{cases}
{% endmath %}

**DON'T** forget to handle case when $$i = 0$$ or $$j = 0$$ or the program will
generate wrong result for strings like `s1 = "a", s2 = "b", s3 = "ab"`.

```java
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        if (s3.isEmpty()) return true;
        if (s1.isEmpty()) return s2.equals(s3);
        if (s2.isEmpty()) return s1.equals(s3);
        
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] == str3[i]) dp[i + 1][0] = true;
            else break;
        }
        
        for (int j = 0; j < str2.length; j++) {
            if (str2[j] == str3[j]) dp[0][j + 1] = true;
            else break;
        }
        
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str3[i + j - 1]) {
                    dp[i][j] |= dp[i - 1][j];
                }
                
                if (str2[j - 1] == str3[i + j - 1]) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        
        return dp[str1.length][str2.length];
    }
}
```
