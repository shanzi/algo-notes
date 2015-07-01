# Implement strStr()

Yes, this is a string search problem. Although the [solution](https://leetcode.com/problems/implement-strstr/solution/)
given by LeetCode says that a brute force solution is sufficient, it is in fact not true.

Either KMP or Boyer-Moore algorithm should be given in an interview if you want to get a good result.
Compared to Boyer-Moore, KMP algorithm is a little simplier as the program to make jump table is simple
enough for human just keep it in mind. After build the jump table, we can use our knowledge to write
the code for searching.

```java
public class Solution {
    private int[] makeNext(String needle) {
        // just keep code of this function in mind!!!
        char[] str = needle.toCharArray();
        int[] next = new int[str.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        
        while (j < str.length - 1) {
            if (k < 0 || str[j] == str[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        
        return next;
    }
    
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        
        int[] next = makeNext(needle);
        int i = 0;
        int j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j >= next.length) {
                    return i - j;
                }
            } else {
                j = next[j];
                if (j < 0) {
                    j = 0;
                    i++;
                }
            }
        }
        
        return -1;
    }
}
```
