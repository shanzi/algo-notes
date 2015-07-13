# Power(x, n)

Calculate `n`th power of a double number `x`, where `n` is an integer.
You must handle cases that:

1. `x` is negative
2. `n` is negative
3. `x = 1` and `n = Integer.MAX_VALUE`
4. `x = 1` and `n = Integer.MIN_VALUE`
5. `x = -1` and `n = Integer.MAX_VALUE`
6. `x = -1` and `n = Integer.MIN_VALUE`

As for `x = 1` we directly return `1`. For `x < 0` we find the result's sign first and calculate `-x`'s
power of `n`. For `n < 0`, we calculate `1/pow(x, -n)`.

```java
public class Power {
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        if (x == 1) return 1.0;
        if (x < 0) return ((n & 1) == 1) ? -myPow(-x, n) : myPow(-x, n);
        else if (n == 0) return 1;
        else if (n < 0) return 1.0 / myPow(x, -n);
        else if ((n & 1) == 1) return myPow(x, n - 1) * x;
        else {
            double r = myPow(x, n >> 1);
            return r * r;
        }
    }
}
```
