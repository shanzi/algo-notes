# Wildcard Matching

This problem is a little hard. At first we should have the following observations:

1. A series of adjoined `*` equals to only one `\*`. So we can skip adjoined `*`s.
2. If a `*` divide the parttern into two parts and the first parts has already perfectly matched
with part of target string, it won't affect if the second part will match the remaining part of the string.
It is because we can always skip any unmatch characters with `*`

Due the the second observation, we can apply gready strategy, let part of parttern match to the most
preceding match in the string possible. Let's we have two pointer `i`, `j`. After each iteration,
we keep `s[0..i]` matching with `p[0..j]`. Let `k` be the last possition we meet a `*` and `l`
that let `s[0..l]` be matched with `p[0..k]`. Obviously, when we meet first `p[j] = '*'`, the corresponding
`i` will be the initial value of `l`. Then if there is an umatch, we jump back to latest `*` and rewind
`j` to `k` and `i` to `l + 1`. We also move `l` one step right at the same time. Then try to continue
match at current `i` and `j` until we meet the end or find something that is impossible to match.

Note in the problem, `?` can match to any characters.

```java
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        
        int i = 0, j = 0, k = -1, l = -1;
        
        char[] str = s.toCharArray();
        char[] pstr = p.toCharArray();
        
        while (j <= pstr.length) {
            while (j < pstr.length && pstr[j] == '*') {
                k = j;
                l = i;
                j++;
            }
            
            if (j < pstr.length && i < str.length && (str[i] == pstr[j] || pstr[j] == '?')) {
                i++;
                j++;
            } else if (k >= 0 && i < str.length) {
                j = k + 1;
                i = ++l;
            } else break;
        }
        
        return j == pstr.length && i == str.length;
    }
}
```
