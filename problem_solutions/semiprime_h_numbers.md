# Semi-prime H Numbers (POJ 3292)

This question gives a new defined prime numbers in a specific number space. We can apply methods similar to
prime sieve method to find all prime H numbers in range and then find all semi-prime H numbers. Then
when a query comes, we use binary search to find the count in $$O(\log N)$$ time.

!CODEFILE "../poj/SemiPrimeHNumbers.java"
