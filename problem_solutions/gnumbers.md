# gNumbers

A number is gNumber if and only if the sum of its digits is a prime number. This problem can be
solved with recursive functions. The key to handle large set is that we do not precalculate prime numbers
first, but for each number, we directly find all its prime factors with the following code:

```java
private static void getFactors(long N) {
    factors.clear();
    for (long i = 2; i * i <= N; i++) {
        if (N % i == 0) {
            factors.add(i);
            while (N % i == 0) N /= i;
        }
    }
    if (N > 1) factors.add(N);
}
```

This method is guaranteed to find all prime factors efficiently. To prove that, at first we know,
all numbers found is a divisor of `N`, then if we have a number `a` that is not prime, we must have
a prime number `p` that `a` is divisible by `p` and `p < a`. But as `p < a` we should have already met
`p` before `a`. And in the code we have kept dividing `N` with `p` until `N` is no longer divisible by `p`.
That is a contradiction. So all numbers we find will be prime divisors of `N`.

To find which player will win, we uses DFS method to let each player always choose the best prime divisor he
can find. If he can not find a prime divisor to guarantee to win, then he must lose as the other player will
make the optimal choice.

!CODEFILE "../apac/2016/RoundB/gNumbers.java"
