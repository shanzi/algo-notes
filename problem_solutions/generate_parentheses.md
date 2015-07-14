# Generate Parentheses

The solution to this problem based on an observation that for any well-formed parentheses string,
given an $$i \in (0, len]$$, we always have that number of left parentheses $$l$$ is greater or
equal to number of right parentheses $$l$$ in substring `s[0..i]` of the original string.
Here $$len = 2\cdot n$$ is the length of that string.

So we can come up with a recursive program that try to add right parentheses only when $$l > r$$.
As $$l \le n$$ and $$len = 2\cdot n$$, at last we will have a string with $$n$$ left and right
parentheses respectly and the arrangement of them will be valid.

```java
public class GenerateParentheses {
    private void generateParenthesis(int left, int right, int n, char[] str, List<String>res) {
        if (left == n && right == n) {
            res.add(String.valueOf(str));
        } else {
            if (left > right) {
                str[left + right] = ')';
                generateParenthesis(left, right + 1, n, str, res);
            }
            
            if (left < n) {
                str[left + right] = '(';
                generateParenthesis(left + 1, right, n, str, res);
            }
        }
    }
    
    public List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        
        char[]str = new char[n * 2];
        generateParenthesis(0, 0, n, str, result);
        return result;
    }
}
```

