import java.util.*;

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
