# Reqular Expression Matching

One solution to this problem is applying dynamic programming method. Let `dp[i][j]` stands for
if parttern `p[0..j - 1]` is matching with `s[0..i - 1]`. At first `dp[0][0]` will be `true`.
The iteration formular is:

1. If `p[j - 1]` match `s[i - 1]` and `dp[i - 1][j - 1]` are true, then `dp[i][j]` are true
2. If `p[j - 1] == '*'` and `p[j - 2]` match `s[i - 1]`, `dp[i][j]` will be true when
either `dp[i - 1][j]` or `dp[i - 1][j - 1]` is true.
3. If `p[j - 1] == '*'` and `p[0..j - 3]` has already been matching with `s[0..i - 1]` then
we can get `dp[i][j]` is true as the character before `*` can appears zero time.

After compressed, the `dp` matrix can just be reduced to two arrays.

```java
public class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        
        if (m == 0 && n == 0) return true;
        
        boolean[] last = new boolean[m + 1];
        boolean[] cur = new boolean[m + 1];
        
        // i = 0;
        last[0] = true;
        for (int j = 1; j <= m; j++) {
            if (j >= 2 && p.charAt(j - 1) == '*') {
                last[j] = last[j - 2];
            }
        }
        
        // i = 1 to n
        for (int i = 1; i <= n; i++) {
            cur[0] = false;
            
            for (int j = 1; j <= m; j++) {
                cur[j] = false;
                
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    cur[j] = last[j - 1];
                }
                
                if (p.charAt(j - 1) == '*') {
                    cur[j] |= (cur[j - 1] || cur[j - 2]);
                    
                    if (j >= 2 && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')) {
                        cur[j] |= last[j - 1] || last[j];
                    }
                }
            }
            
            boolean[] temp = last;
            last = cur;
            cur = temp;
        }
        
        return last[m];
    }
}
```
