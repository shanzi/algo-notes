# Count Primes

Using sieve method. Have a look at the sample code below and note that:

1. All even integer except 2 is not prime.
2. Pay attention to the start position and increment of sieving loop (the loop against `j`).
3. We can use a `BitSet` to save a lot of space.
4. One is not prime.

More instructions at LeetCode's [problem hint for this problem](https://leetcode.com/problems/count-primes/).

```java
public class CountPrimes {
    public int countPrimes(int n) {
        BitSet set = new BitSet(n);
        if (n <= 2) return 0;
        
        int count = 1;
        for (int i = 3; i < n; i += 2) {
            if (set.get(i)) continue;
            count++;
            for (long j = i *(long)i; j < n; j += 2 * i) {
                set.set((int)j);
            }
        }
        return count;
    }
}
```
