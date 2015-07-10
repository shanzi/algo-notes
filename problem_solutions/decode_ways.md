# Decode Ways

This is a dynamic programing problem. The cases that should be handled including:

1. `'0' < str[i] <= '9'`. In this case, this char can decode one letter independently.
2. `i > 0, str[i - 1] == '1'`. In this case, `str[i - 1]` along with `str[i]` can represent 10 to 19, which is valid
   encoding.
3. `i > 0, str[i - 1] == '2', '0' <= str[i] <= '6'`. In this case, `str[i - 1]` along with `str[i]`
can represent 20 to 26, which is valid encoding.

Empty string should be handle separately.

```java
public class DecodeWays {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > '0') {
                dp[i + 1] += dp[i];
            }
            
            if (i > 0 && s.charAt(i - 1) == '1') {
                dp[i + 1] += dp[i - 1];
            }
            
            if (i > 0 && s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                dp[i + 1] += dp[i - 1];
            } 
        }
        
        dp[0] = 0;
        
        return dp[s.length()];
    }
}
```
