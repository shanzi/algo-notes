public class StringToInteger {
    public int myAtoi(String string) {
        long sign = 1;
        long integer = 0;
        int state = 0;
        int i = 0;
        char[] str = string.toCharArray();
        
        while (i < str.length) {
            char ch = str[i];
            if (state == 0) {
                // this is sign mode
                if (ch == '-') {
                    sign = -1;
                    state = 1;
                    i++;
                }
                else if (ch == '+') {
                    state = 1; // switch to integer mode, so -+ and  +- is not valid
                    i++; // skip plus sign or spaces in the head
                }
                else if (ch == ' ') i++;
                else if (ch >= 0 && ch <= '9') state = 1; // switch to integer mode
                else if (ch == '.') {
                    state = 2; // swich to decimal mode
                    i++;
                } else break; // unexcepted char

            } else if (state == 1) {
                // this is integer mode
                if (ch >= '0' && ch <= '9') {
                    integer = integer * 10 + (ch - '0');
                    if (integer > -(long)Integer.MIN_VALUE) break;
                } else if (ch == '.') {
                    state = 2; // switch to decimal mode
                } else if (ch == 'E' || ch == 'e') {
                    state = 3; // switch to power mode
                } else break; // unexcepted char
                
                i++;
            }
        }
        
        long res = sign * integer;
        if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else if ( res < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return (int)res;
    }
}
