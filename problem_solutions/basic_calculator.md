# Basic Calculator

Implement a simple calculator is also a common problem. The ultimate form
of similar problem is expression evaluation. But problems we meet in practical
interview are usually a simplified version.

LeetCode has two problems of this type: [Basic Calculator](https://leetcode.com/problems/basic-calculator-ii/)
and [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/).

The basic idea to solve this kind of problem is to use `Stack`. We can either use
one stack to save both operator and operating number or use two stacks to save
operator and operating number separatedly. The priority of operators are important
and some problems is asked to support parenthesis.

Here we give answers to the two problems on LeetCode. The first supports `+`, `-` and parenthesis
while the second supports `+`, `-`, `*`, `/`. One is implemented with only one stack.
The other is implemented with two stacks to save number and operator separatedly.

```java
class Token {
    boolean isOperator;
    char operator;
    int value;
    
    Token(int value) {
        isOperator = false;
        this.value = value;
    }
    
    Token(char operator) {
        isOperator = true;
        this.operator = operator;
    }
}

public class BasicCalculator {
    private List<Token> getTokens(String s) {
        LinkedList<Token> tokens = new LinkedList<Token>();
        Token last = null;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch >= '0' && ch <= '9') {
                if (last == null || last.isOperator) {
                    last = new Token(0);
                    tokens.addLast(last);
                }
                
                last.value *= 10;
                last.value += (ch - '0');
            } else if (ch != ' ') {
                last = new Token(ch);
                tokens.addLast(last);
            }
        }
        
        return tokens;
    }
    
    private void squashResult(Stack<Token> stack) {
        if (stack.isEmpty()) return;
        Token res = stack.pop();
        if (!stack.isEmpty()) {
            Token operator = stack.pop();
            int leftValue = 0;
            
            if (!stack.isEmpty()) {
                Token leftToken = stack.peek();
                if (!leftToken.isOperator) {
                    leftValue = leftToken.value;
                    stack.pop();
                }
            }
            
            if (operator.operator == '+') {
                res.value = leftValue + res.value;
            } else if (operator.operator == '-') {
                res.value = leftValue - res.value;
            } else {
                stack.push(operator);
            }
        }
        stack.push(res);
    }
    
    public int calculate(String s) {
        List<Token> tokens = getTokens(s);
        Stack<Token> stack = new Stack<Token>();
        for (Token t : tokens) {
            if (t.isOperator) {
                if (t.operator == ')') {
                    Token valueToken = stack.pop();
                    stack.pop(); // pop left parenthesis
                    stack.push(valueToken);
                    squashResult(stack);
                } else {
                    stack.push(t);
                }
            } else {
                stack.push(t);
                squashResult(stack);
            }
        }
        
        return stack.peek().value;
    }
}
```

```java
public class BasicCalculatorII {
    public int calculate(String s) {
        Stack<Integer> numberStack = new Stack<Integer>();
        Stack<Character> operatorStack = new Stack<Character>();
        
        char[] str = s.toCharArray();
        int i = 0;
        while (i < str.length) {
            if (str[i] >= '0' && str[i] <= '9') {
                int num = 0;
                while (i < str.length && str[i] >= '0' && str[i] <= '9') {
                    num = num * 10 + (str[i] - '0');
                    i++;
                }
                
                numberStack.push(num);
                
                if (!operatorStack.isEmpty()) {
                    if (operatorStack.peek() == '*') {
                        operatorStack.pop();
                        int res = numberStack.pop() * numberStack.pop();
                        numberStack.push(res);
                    } else if (operatorStack.peek() == '/') {
                        operatorStack.pop();
                        int divider = numberStack.pop();
                        numberStack.push(numberStack.pop() / divider);
                    }
                }
                
            } else if (str[i] == '+' || str[i] == '-'){
                if (!operatorStack.isEmpty()) {
                    char op = operatorStack.pop();
                    if (op == '+') {
                        numberStack.push(numberStack.pop() + numberStack.pop());
                    } else {
                        numberStack.push(-numberStack.pop() + numberStack.pop());
                    }
                }
                operatorStack.push(str[i++]);
            } else if (str[i] == '*' || str[i] == '/') {
                operatorStack.push(str[i++]);
            } else {
                i++;
            }
        }
        
        while (numberStack.size() > 1) {
            char op = operatorStack.pop();
            if (op == '+') {
                numberStack.push(numberStack.pop() + numberStack.pop());
            } else {
                numberStack.push(-numberStack.pop() + numberStack.pop());
            }
        }
        
        return numberStack.pop();
    }
}
```
