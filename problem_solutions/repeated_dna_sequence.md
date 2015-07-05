# Repeated DNA Sequence

The solution to this problem is simple, we use brute force method and check the strings
in 10 characters one by one and using a `HashSet` to verify duplication. However,
to pass the test cases we have to reduce the memory cost as using a `HashSet` to
preserve all appeared substrings of length 10 will cost memory a lot. The simplest
way to do this is using an integer to represent the string.

As the string only contains 4 types of characters `A`, `C`, `G` and `T`, it is possible
to use 2 bit to represent this four characters. Here we let `A = 0`, `C = 1`,
`G = 2`, `T = 3`. In java, an integer as 32 bits, so a substring of length 10 can be
compressed into an integer. We uses a `HashSet<Integer>` instead of `HashSet<String>`
to save appeared substrings.

After compression, the space cost of this program is reduced by 10 times(althougth
it is still $$O(2^{20})$$. The time cost of this program is $$O(N)$$.

```java
public class Solution {
    private int numberForChar(char ch) {
        switch(ch) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            default:
                return 3;
        }
    }
    
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<Integer> dupSet = new HashSet<Integer>();
        HashSet<String> res = new HashSet<String>();
        int mask = ~(-1 << 20);
        int l = 0;
        for (int i = 0; i < s.length(); i++) {
            l = (l << 2 | numberForChar(s.charAt(i))) & mask;
            if (dupSet.contains(l)) {
                res.add(s.substring(i - 9, i + 1));
            } else if (i >= 9) {
                dupSet.add(l);
            }
        }
        
        ArrayList<String> list = new ArrayList<String>(res);
        return list;
    }
}
```
