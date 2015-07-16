# Shortest Palindrome

To generate shortest palindorme, we first find the longest palindrome whose left is the start of string.
For example, we have a string `str`, the longest palindrome we find is `str[0..l]`, then the string
this question is asked will be `reverse(str[l..end]) + string[0..l]`.

To find such palindrome fast, we apply KMP algorithm and search pattern in the origin string reversely.

Let's say current position is i, to the next matching length is k. That means k characters
after i is the same as the reverse of the first characters. When i meet k or i moves over
k, we know that there is a palindrome prefix of s has been found. The first palindrome prefix

```java
public class ShortestPalindrome {
    int[] makeNext(char[] str) {
        int [] next = new int[str.length];
        next[0] = -1;
        
        int l = -1, i = 0;
        
        while (i < str.length - 1) {
            if (l < 0 || str[l] == str[i]) {
                next[++i] = ++l;
            } else {
                l = next[l];
            }
        }
        
        return next;
    }
    
    public String shortestPalindrome(String s) {
        if (s.length() < 2) return s;
        
        char[] str = s.toCharArray();
        int[] next = makeNext(str);
        
        int i = str.length - 1, k = 0;
        
        while (i > k) {
            if (k < 0 || str[i] == str[k]) {
                i--;
                k++;
            } else {
                k = next[k];
            }
        }
        
        int l = 0;
        
        if (i == k) {
            l = i * 2 + 1;
        } else {
            l = k * 2;
        }
        
        StringBuilder sbd = new StringBuilder();
        sbd.append(s.substring(l));
        sbd.reverse();
        sbd.append(s);
        return sbd.toString();
    }
}
```
