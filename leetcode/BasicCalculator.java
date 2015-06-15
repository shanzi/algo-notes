import java.util.*;

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

