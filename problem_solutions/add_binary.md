# Add Binary

Given two numbers in their binary representation. The two numbers is very large so that
they are both represented as `String`. Add the two number and return the result in the same
representation way.

The simplest way to solve this problem is to use Java's `BigInteger`:

```java
import java.math.BigInteger;

public class AddBinary {
    public String addBinary(String a, String b) {
        BigInteger m = new BigInteger(a, 2);
        BigInteger n = new BigInteger(b, 2);
        return m.add(n).toString(2);
    }
}
```

But this may not be a valid solution in an interview. Another way is to performing binary operations
instead of using `+` operator. It is also a common question. Let's think first for add two `int` with out `+`.
We know that `x ^ y` is in fact adding two integer without carries. And we can get curries by `(x & y) << 1`.
So `(x ^ y) + ((x & y) << 1)` is the same as `x + y`. But `+` is not available, so we can recursively or looply calls
our plus function until the carries becames zero.

```java
public class ImplementPlusOperator {
    public int plus(int a, int b) {
        int remain = 0;
        int carry = 0;
        do {
            remain = a ^ b;
            carry = (a & b) << 1;
            a = remain;
            b = carry;
        } while (carry != 0)
        return remain;
    }
}
```

So the same to our binary string, we just need to implement our own `XOR`, `AND` and left shift function
for the string.

```java
public class Solution {
    private String xorBinary(String a, String b) {
        String longer;
        String shorter;

        if (a.length() >= b.length()) {
            longer = a;
            shorter = b;
        } else {
            longer = b;
            shorter = a;
        }
        char[] result = longer.toCharArray();
        int delta = longer.length() - shorter.length();
        for (int i = 0; i < shorter.length(); i++) {
            char ch = shorter.charAt(i);
            if (ch == '1') {
                if (result[delta + i] == '1') result[delta + i] = '0';
                else result[delta + i] = '1';
            }
        }
        return String.valueOf(result);
    }

    private String andBinary(String a, String b) {
        String longer;
        String shorter;

        if (a.length() >= b.length()) {
            longer = a;
            shorter = b;
        } else {
            longer = b;
            shorter = a;
        }
        char[] result = new char[longer.length()];
        Arrays.fill(result, '0');
        int delta = longer.length() - shorter.length();
        for (int i = 0; i < shorter.length(); i++) {
            char ch = shorter.charAt(i);
            if (ch == '1' && longer.charAt(delta + i) == '1') result[delta + i] = '1';
        }
        return String.valueOf(result);
    }

    private String leftShift(String s) {
        s = s + "0";
        int first1 = s.indexOf('1');
        return s.substring(first1);
    }

    private boolean isZero(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') return false;
        }
        return true;
    }

    public String addBinary(String a, String b) {
        String carry = andBinary(a, b);
        String remain = xorBinary(a, b);
        if (!isZero(carry)) {
            return addBinary(remain, leftShift(carry));
        }
        return remain;
    }
```
