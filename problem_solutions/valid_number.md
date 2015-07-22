# Valid Number

Like [String To Integer](problem_solutions/string_to_integer.md), this is a problem need you
take care of many cases carefully. We uses state transition or
[Finite State Machine](https://en.wikipedia.org/wiki/Finite-state_machine) to solve this problem.

```java
public class ValidNumber {
    
    static final int PREFIX = 0;
    static final int PRE_POWER = 1;
    static final int PRE_DECIMAL = 2;
    static final int SIGN = 3;
    static final int POWER_SIGN = 4;
    
    static final int INTEGER = 5;
    static final int DECIMAL = 6;
    static final int POWER = 7;
    static final int SUFFIX = 8;
    
    public boolean isNumber(String s) {
        int state = PREFIX;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (state == PREFIX) {
                if (ch == ' ') continue;
                else if (ch == '-' || ch == '+') state = SIGN;
                else if (ch >= '0' && ch <= '9') state = INTEGER;
                else if (ch == '.') state = PRE_DECIMAL;
                else return false;
            } else if (state == SIGN) {
                if (ch >= '0' && ch <= '9') state = INTEGER;
                else if (ch == '.') state = PRE_DECIMAL;
                else return false;
            } else if (state == PRE_DECIMAL) {
                if (ch >= '0' && ch <= '9') state = DECIMAL;
                else return false;
            } else if (state == PRE_POWER) {
                if (ch >= '0' && ch <= '9') state = POWER;
                else if (ch == '+' || ch == '-') state = POWER_SIGN;
                else return false;
            } else if (state == POWER_SIGN) {
                if (ch >= '0' && ch <= '9') state = POWER;
                else return false;
            } else if (state == INTEGER) {
                if (ch >= '0' && ch <= '9') continue;
                else if (ch == '.') state = DECIMAL;
                else if (ch == 'e' || ch == 'E') state = PRE_POWER;
                else if (ch == ' ') state = SUFFIX;
                else return false;
            } else if (state == DECIMAL) {
                if (ch >= '0' && ch <= '9') continue;
                else if (ch == 'e' || ch == 'E') state = PRE_POWER;
                else if (ch == ' ') state = SUFFIX;
                else return false;
            } else if (state == POWER) {
                if (ch >= '0' && ch <= '9') continue;
                else if (ch == ' ') state = SUFFIX;
                else return false;
            } else if (state == SUFFIX) {
                if (ch == ' ') continue;
                else return false;
            }
        }
        
        return (state >= INTEGER);
    }
}
```
