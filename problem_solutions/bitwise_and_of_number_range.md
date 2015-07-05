# Bitwise AND of Numbers Range

Given two integer `m` and `n` while `0 <= m <= n <= Integer.MAX_VALUE`, return the result of
`m & (m + 1) & (m + 2) & ... & (n - 1) & n`. To work out the result by `AND` the integers
one after one is not feasible as the range can be very large.

After a little consideration, we can easily get that in the result one bit position can be `1` if and only
if there are no integer in the range that has this bit position is zero. Thus, we can come up with a fast
solution:

1. for each bit position in `n`, if that position has bit `1`, we find the larges number less than `n`
with this position is `0`. If this number is larger than `m`, then this bit position in the result will be `0`.
2. We know that bit positions of `1`s in the result must be a subset of that from `m & n`. So we let the
initial result to be `m & n`.

We know that `n & -n` returns an integer with only the lowest bit position of `1` remain to be `1` and all
other bit positions became `0` and `n & (n - 1)` turns the `1` at the lowest bit position to be `0`.
Make use of the two formulars above we can come up with a solution which will be faster than iterate
each bit position:

```java
public class BitwiseANDOfNumberRange {
    
    public int rangeBitwiseAnd(int m, int n) {
        int res = m & n;
        
        while (n > 0) {
            if ((((n & -n) - 1) | (n & (n - 1))) > m) {
                res &= ~(n & -n);
            }
            n =  n & (n - 1);
        }
        
        return res;
    }
}
```

There can also be a solution in reverse consideration. Instead of finding the largest number
below `n`, we can also find the least number great than `m`. But you should be very careful
as in this case, we are finding number greater than current and it might easily exceed
range if we have `m = Integer.MAX_VALUE - 1` and `n = Integer.MAX_VALUE` and get a wrong result.
So we have to use `long` instead of `int` for bit operations.

```java
public class BitwiseANDOfNumberRange {
    
    public int rangeBitwiseAnd(int m, int n) {
        long res = m & n;
        long l = m;
        long r = n;
        
        while (l > 0) {
            if ((l | ((l & -l) - 1)) + 1 <= r) {
                res &= ~(l & -l); 
            }
            l = l & (l - 1);
        }
        
        return (int)res;
    }
}
```

