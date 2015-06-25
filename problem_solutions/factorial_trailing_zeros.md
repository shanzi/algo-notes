# Factorial Trailing Zeros

Classical problem. This problem is in fact to count the number of fives from 1 to n as $$2 \times 5 = 10$$
and the number of 2s is obviously greater than that of 5s. So when there is a 5, there will be one more
trailing zero in $$n!$$. We have to solve the problem in a time of $$O(\log n)$$ and we should also
take integers that are power of 5 into consider. The best answer is that we divide `n` by 5 repeatly
and added up all the values in the process.

```java
public class FactorialTrailingZeros {
    public int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            n /= 5;
            count += n;
        }
        return count;
    }
}
```
