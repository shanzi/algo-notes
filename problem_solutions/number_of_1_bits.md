# Number of 1 Bits

Simple problem but has multiple solutions.

The first is that we just iterate over all bits and count the ones:

```java
public class NumberOfOneBits {
    public int hammingWeight(int n) {
        int count = 0;
        for(int i = 0; i < 32; i++) {
            if (n & 1 == 1) count++;
            n >>= 1;
        }
        return count;
    }
}
```

This solution is simple but it depends on the integer's type. It demands that the integer
is 32 bits so it need to be modified in different language and machine architecture.
The loop executes the fixed amount of times no matter which integer the number is and
is thought not time efficient enough.

One alternative solution might be make use of an knowledge that `n & (n - 1)` returns an
integer that all bits is the same as `n` except the first `1` at the lowest bit position
turned into `0`. So everytime we perform a `n = n & (n - 1)`, there will be one less `1`
in the binary representation of `n`.

```java
public class NumberOfOneBits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
```

In the code above, `n & (n - 1)` is in fact equivalent to `n & ~(n & -n)` as `n & -n` will
generate the integer with only the lowest `1` bit in `n` to left `1` and all other bits become `0`.
