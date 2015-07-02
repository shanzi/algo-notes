# String To Integer

String to integer, aka. `atoi`, is a classic problem. The corresponding
[problem](https://leetcode.com/problems/string-to-integer-atoi/) is in fact a lot simple
as you actually do not need to consider power start from `E` or `e`. So the only problem
is to handle sign and number exceed. Integer that higher than `Integer.MAX_VALUE` and
and lower than `Integer.MIN_VALUE` will be cut off and just return the corresponding limit.

Here are two different solutions:

```java
public class StringToInteger {
    public int atoi(String str) {
        char[] strchar = str.toCharArray();
        int i = 0;
        while (i < strchar.length && strchar[i] == ' ') i++;
        long sign = 1;
        long value = 0;
        
        if (i >= strchar.length) return 0;
        
        if (strchar[i] == '-') {
            sign = -1;
            i++;
        }
        else if (strchar[i] == '+') i++;
        
        if (i < strchar.length && (strchar[i] < '0' || strchar[i] > '9')) return 0;
        
        while (i < strchar.length && strchar[i] >= '0' && strchar[i] <= '9') {
            value *= 10;
            value += (long)(strchar[i] - '0');
            i++;
            if (value > 2147483647) break;
        }
        
        value = Math.abs(value) * sign;
        
        if (value > 2147483647) return 2147483647;
        if (value < -2147483648) return -2147483648;
        return (int)value;
    }
}
```

!CODEFILE "../leetcode/StringToInteger.java"
