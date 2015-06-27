# Max XOR sub sequence

Given an array contains only positive integers, find a sub sequence that after
reduce all elements by `XOR` operator, the result is the largest. Return the largest number.

A brute force solution is iterate over every range pairs $$(i, j)$$ and find the
one with largest result. This solution has a time cost of $$O(N^2)$$ in its best.

```java
class MaxXORSubSequence {
    public int findMaxBruteForce(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            int m = 0;
            for (int j = i; j < array.length; j++) {
                m ^= array[j];
                max = Math.max(max, m);
            }
        }
        return max;
    }
}
```

It is likely that there are no $$O(N)$$ solution to this problem, but the solution
is still able to be improved. `XOR` operator has a property that for any integer `x`,
`x ^ x = 0` and `x ^ 0 = x`. So for a range of XOR `R[j..i]`, it equals
`R[0..j-1] ^ R[0..i]`. Assume `j < i`, for any `i` let's consider all sub sequences
end at that position. If we can find one with largest result for one `i` faster and
pick the max from all `i`, we will be able to get the answer faster.

To iterate each `j < i` won't reduce the time cost. We can turn to a construction way.
Say `R[i] = R[0..i]`, from the highest bit of `R[i]`, if it is a `0`, we try to find
a set of `R[j]` where `j < i` that `R[j] ^ R[i]` has an 1 at that position. Conversely,
it is a `1`, we find a set let the `1` to be left. After this, we check the second highest bit
and find sub set from the set from last iteration. We aways add `0` in the set to enable
to find a way to let `R[i]` to be itself left.

To perform the sub set finding operation fast, we can use a Trie or Prefix Tree.
The following code shows a combination example of this algorithm. We uses the first
algorithm to validate the result.

!CODEFILE "../common/MaxXORSubSequence.java"
