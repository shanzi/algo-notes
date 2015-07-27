# Different Ways to Add Parentheses

This problem is equivalent to calculate every possible results for every perform order of operators.
Divide and conquer method is suitable for this problem. To caculate every possible results,
at first we pick one of the operators to perform first, then for the expressions on the left
and right of that operator, we recursively find every possible results. Then for each pair of
operands with the first comes from results of left and the second from results of right,
we perform the operator and added the result to list.

```java
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        boolean flag = true;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*') {
                flag = false;
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));

                for (Integer a : left)
                    for (Integer b: right) {
                        switch (ch) {
                            case '+':
                               result.add(a + b); 
                               break;
                            case '-':
                               result.add(a - b); 
                               break;
                            case '*':
                               result.add(a * b); 
                               break;
                        }
                    }
            }
        }

        if (flag) {
            result.add(Integer.parseInt(input));
        }

        return result;
    }
}
```
