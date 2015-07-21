# Minimum Window Substring

This is a simple problem if the string `T` only contains disctinct characters.
However, based on the solution to simple problem, we can get this problem's solution easily.

We apply sliding window method and use two count array to hold character counts in `T` and current slinding
window in `S` respectively. We uses a int variable to count how many characters in the sliding
window is less than or equal to character counts in `T`. That is, for a character `c`, we only
count it when the number it appears is less than that in `T`. When moving the right pointer
to narrow the window we also only decrease count when the number drops below that in `T`.

```java
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (t.isEmpty()) return t;
        
        int[] tcount = new int[128];
        int[] scount = new int[128];
        int matchcount = 0;
        
        for (int i = 0; i < t.length(); i++) {
            tcount[t.charAt(i)]++;
        }
        
        int i = 0;
        int j = 0;
        String res = "";
        
        while (j < s.length()) {
            char chj = s.charAt(j++);
            scount[chj]++;
            if (scount[chj] <= tcount[chj]) matchcount++;
            
            while (matchcount >= t.length()) {
                if (res.isEmpty() || j - i < res.length()) res = s.substring(i, j);
                
                char chi = s.charAt(i++);
                scount[chi]--;
                if (scount[chi] < tcount[chi]) matchcount--;
            }
        }
        
        return res;
    }
}
```
