# Longest Valid Parentheses

This is a hard problem. At first we have the following observations:

1. If we meet a `)` without matching `(` in the left, then all sub sequence contains it won't be valid,
so we can regard string after that character as a independent sub problem.
2. If we can find a match, then the sub sequence from last unmatched `(` to current `)` will be a
sub sequence contains valid parentheses.

We can use a stack to store the position of left parentheses:

```java
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        
        int max = 0;
        int left = -1;
        
        for (int i = 0;i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') stack.push(i);
            else {
                if (stack.isEmpty()) {
                    left = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) max = Math.max(max, i - left);
                    else max = Math.max(max, i - stack.peek());
                }
            }
        }
        

        return max;
    }
}
```


To save space, we can also make use of the array to simulate a stack:

```java
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int left = -1, l = -1, max = 0;
        char[] str = s.toCharArray();
        
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                l = i;
            } else {
                if (l == left) {
                    left = i;
                    l = i;
                } else {
                    str[l--] = '.';
                    while(l > left && str[l] != '(') l--;
                    max = Math.max(max, i - l);
                }
            }
        }
        
        return max;
    }
}
```

