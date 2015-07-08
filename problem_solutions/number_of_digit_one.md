# Number of Digit One (Two)

Given `n`, count how many times digit `1`(or `2`) appears in decimal representation of numbers from `0` to `n`,
inclusively. This is a traditional interview question. The key is to use recursion calls and take care current digit
position carefully. We must handle the cases that current digit is less than, equal to or greater than `1` (or `2`).

Let's take digit one for example, for a number `123`, we have to first find how many `1` appears in from `0` to `99`,
then we find how many digit appears from `0` to `23`. As we have `1` at the highest digit position,
we must add another `23 + 1 = 24` to the count. We recursively calculate count of `99` and `23`.

For a number `234`, apart from count from `99` and `34`, we have to do more things. At first, if we fix the highest
digit from `0` to `1`, `99` pattern repeated twice, so we multipy the count from `99` with `2`.
When we fixed the highest digit to be `1`, then from `100` to `199` we have 100 more digit one, so we add 100.

Based on this idea, we get the solution:

```java
public class NumberOfDigitOne {
    public int countDigitOne(int n) {
        if (n < 1) {
            return 0;
        } else if (n < 10) {
            return 1;
        }
        
        int l = 1;
        while (n / l >= 10) l *= 10;
        
        int r = n / l;
        int res = r * countDigitOne(l - 1) + countDigitOne(n % l);
        if (r > 1) res += l;
        else if (r == 1) {
            res += (n % l) + 1;
        }
        return res;
    }
}
```

Similarly, we can also give the solution of *Number of Digit Two*:

```java
public class NumberOfDigitTwo {
    public int countDigitTwo(int n) {
        if (n < 2) {
            return 0;
        } else if (n < 10) {
            return 1;
        }
        
        int l = 1;
        while (n / l >= 10) l *= 10;
        
        int r = n / l;
        int res = r * countDigitOne(l - 1) + countDigitOne(n % l);
        if (r > 2) res += l;
        else if (r == 2) {
            res += (n % l) + 1;
        }
        return res;
    }
}
```
