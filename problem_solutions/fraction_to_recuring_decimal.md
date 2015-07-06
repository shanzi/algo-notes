# Fraction to Recuring Decimal

String representation of a decimal comes from the quotient of to integer is
either of finite length or has a recuring sequence in its fractional part.
The ends of the recuring part is at the position we get a remain value twice and
the begin of recuring starts at the position where the same remain first occurs.
So we just preserve remain values we meet during division along with their position
and every time we get a remain, we check if it has appeard. We will also end the loop
when we get a remain of `0`.

Although the basic idea to solve this problem is simple, we have a lot of things
to pay attention on:

1. We uses `numerator ^ donominator` to decide if the result is negative.
Note that if `numerator = 0` and `denominator < 0`, the expression returns a
negative value, but the result of division is neither positive nor negative --- it is zero.
We should not add a negative sign to the string in this case.
2. If we divide `Integer.MIN_VALUE` by `-1`, we will get an integer that exceed the range of `int`.
So at first we need to cast values to to `long`.
3. We use a `HashSet<Long, Integer>` to save the mapping from remain to the position it first appears.
But `set.get(i)` returns an `Integer` object so that if we are using `StringBuilder`'s `insert` function
to insert a `char`, there will be an ambiguous error. We can use either `sbd.insert((int)set.get(remain), '(')`
or `sbd.insert(set.get(remain), "("))`.

```java
import java.util.*;

public class FractionToRecuringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sbd = new StringBuilder();
        
        if ((numerator ^ denominator) < 0) sbd.append('-');
        
        long n = numerator;
        long d = denominator;
        
        n = Math.abs(n);
        d = Math.abs(d);
        
        long res = n / d;
        long remain = n % d;
        
        sbd.append(res);
        if (remain == 0) return sbd.toString();
        
        sbd.append('.');
        HashMap<Long, Integer> remainMap = new HashMap<Long, Integer>();
        remainMap.put(remain, sbd.length());
        
        do {
            remain *= 10;
            res = remain / d;
            remain %= d;
            
            sbd.append(res);
            
            if (remain == 0) break;
            if (remainMap.containsKey(remain)) {
                sbd.insert((int)remainMap.get(remain), '(');
                sbd.append(')');
                break;
            }
            
            remainMap.put(remain, sbd.length());
            
        } while (true);
        
        return sbd.toString();
    }
}
```
