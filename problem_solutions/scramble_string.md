# Scramble String

Two time of solutions: recursive solution with cache and bottom up dynamic programing.

As for recursive solution, we given two string, we test every split position and check if
it is scrameble string recursively. To avoid duplicated processing, we use a `HashMap` for cache.

```java
public class ScrambleString {
    private boolean isScramble(String s1, String s2, Map<String, Boolean> cache) {
        
        if (s1.equals(s2)) return true;
        if (s1.length() <= 1) return false;
        String cacheKey = s1 + " " + s2;
        if (cache.containsKey(cacheKey)) return cache.get(cacheKey);
        
        boolean flag = false;
        int n = s1.length();
        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i), cache)
                && isScramble(s1.substring(i), s2.substring(i), cache)) {
                    flag = true;
                    break;
                }
            
            if (isScramble(s1.substring(0, i), s2.substring(n - i, n), cache)
                && isScramble(s1.substring(i, n), s2.substring(0, n - i), cache)) {
                    flag = true;
                    break;
                }
        }
        
        cache.put(cacheKey, flag);
        return flag;
    }
    
    public boolean isScramble(String s1, String s2) {
        if (s1.isEmpty() && s2.isEmpty()) return true;
        
        HashMap<String, Boolean> cache = new HashMap<String, Boolean>();
        return isScramble(s1, s2, cache);
    }
}
```

In dynamic programming solutions, we use four loops. The idea is for every start point `i` in `s1` and `j` in `s1`
to see if two string of the same length `l` are scramble strings. We build the dynamic programming process
from `l = 1` to `n` and at last we get the result with `i = 0`, `j = 0` and `l = n`.

```java
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        int len = s1.length();
        boolean[][][] cache = new boolean[len][len][len+1];
        
        for (int l = 1; l <= len; l++) {
            for (int i = 0; i <= len - l; i++) {
                for (int j = 0; j <= len - l; j++) {
                    if (l == 1) {
                        cache[i][j][l] = (s1.charAt(i) == s2.charAt(j));
                    } else {
                        for (int k = 1; k < l; k++) {
                            if ((cache[i][j][k] && cache[i + k][j + k][l - k]) ||
                            (cache[i][j + l - k][k] && cache[i + k][j][l - k])) {
                                cache[i][j][l] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        return cache[0][0][len];
    }
}
```
