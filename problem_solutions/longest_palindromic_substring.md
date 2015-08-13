# Longest Palindromic Substring

A brute-force solution is check with center at $$i = 0, 1, 2, \cdots, n$$ and try to retrieve
longest palindromic substring during this procedure. As we have to consider cases that length of substrings
a odd or even respectively, so we have to traverse through the string twice. In worst case we have to check
$$O(N^2)$$ elements. So the time cost is $$O(N^2)$$ while the space cost is constant.

A better solution in running time is Manacher's algorithm. To be convenient, at first we insert boundaries
between characters. so `abc` becomes `#a#b#c#` where `#` is a special character that won't appears in the original
string. By doing this, we can uniform the cases of odd and even length of substring. To be more clear,
if we found a substring with odd length, the center will be on a normal character from original string,
if the length is even, the center will be on a `#`.

After doing this, as to find longest palindromic substring, we use an array `p` whose `p[i]` holds longest distance
can we go left or right of the longest palindromic substring centered at `i`. Then we have the following propositions:

1. Given a center `C` and the longest palidromic substring from `L` to `R`. And for any `L < j < C`,
if `j - p[j] >= L`, that is the longest palindromic substring centered at `j` is totally contained by the longer
substring centered at `C`.
2. Let the corresponding index of `i` mirrored by `C` to be `i`, then we must have the longest palindromic
substring centered at `i` is totally contained by that centered at `C` because the longer substring centered
at `C` is palindrome, so in fact we have `p[i] = p[j]`.
3. if `j - p[j] < L` or `R - i > p[j]`, that means the palindrome centered at `j` is only partly containd by
that centered at `C`, so we can not say `p[i] = p[j]`. But at this time we can at least be sure that the overlapping
part is guaranteed to be palindromic. Along this 2 we can get `p[i] = Math.min(p[j], R - i)`.
4. Following 3, as for part beyond the boundary `[L, R]` we have to check one char by one char to extend `p[i]` to
its limit, thus get the final `p[i]`.
5. As `i + p[i]` has extend beyound the boundary `[L, R]`, we should now change the center to `i`
as for any `i < k <= R` we will can get no longer palindromic substring in `[L, R]` than in the new boundary `[L', R']`
centered at `i`.

Below are an example implementation:

```java
public class LongestPalindromicSubstring {
    private char[] addBoundaries(String s) {
        char[] result = new char[s.length() * 2 + 1];
        
        for(int i = 0; i < s.length(); i++) {
            result[i * 2 + 1] = s.charAt(i);
        }
        
        return result;
    }
    
    public String longestPalindrome(String s) {
        char[] str = addBoundaries(s);
        int[] p = new int[str.length];
        
        int C = 0, R = 0;
        for (int i = 1; i < str.length - 1; i++) {
            p[i] = (i < R) ? Math.min(p[C * 2 - i], R - i) : 0;
            
            while (i - p[i] - 1 >= 0 && i + p[i] + 1 < str.length && str[i - p[i] - 1] == str[i + p[i] + 1]) p[i]++;
            
            if (i + p[i] > R) {
                C = i;
                R = i + p[i];
            }
        }
        
        int center = 0;
        
        for (int i = 1; i < p.length - 1; i++) {
            if (p[center] < p[i]) {
                center = i;
            }
        }
        
        if (p[center] == 0) return "";
        else return s.substring((center - p[center]) / 2, (center + p[center]) / 2);
    }
}
```
