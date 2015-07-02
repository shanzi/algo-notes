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
to find a way to let `R[i]` to be itself left. Time cost of this algorithm is $$O(N\log N)$$
as query a Trie has a time cost of $$O(\log N)$$.

To perform the sub set finding operation fast, we can use a Trie or Prefix Tree.
The following code shows a combination example of this algorithm. We uses the first
algorithm to validate the result.

!CODEFILE "../common/MaxXORSubSequence.java"

## Similar questions

There are two similar questions, which is described at stackoverflow:
[Two elements in array whose xor is maximum](http://stackoverflow.com/questions/9320109/two-elements-in-array-whose-xor-is-maximum)
and
[Finding the max XOR of two numbers in an interval](http://cs.stackexchange.com/questions/29508/finding-the-max-xor-of-two-numbers-in-an-interval-can-we-do-better-than-quadrat).

The first one is a mutation of this problem, but the answer given provide a way
other than Trie to find a pair of number.

In the second question, the number in the range is sorted and all integers a from a interval.
Thus we will have a $$O(\log N)$$ solution. Let's say the interval is from `x` to `y` inclusive.
From higher bits to lower bits there will be some bits are the same in `x` and `y`, thus
any integer between `x` and `y` will have the same prefix. Then at the first bit that is different,
as `y` is greater than `x`, thus there must be a `1` at that position of `y` and `0` of `x`.

And we know, if we drop the common prefix and only consider bits from that prosition,
`01111..1` must be greater than `x` and less than `y`. The same to `10000..0`. So let these two
suffix perform `XOR`, we will get `11111..1`. Thus, the largest `XOR` result from two elements
in the interval must in a form like `0..000111..1` and `1` begins at the highest different bits
of `x` and `y`.

We can use `x ^ y` to find that position. Sample code:

```java
class MaxXORInAnInterval {
    public int max(int x, int y) {
        if (x < 0 && y >=0) x = 0;

        int diff = x ^ y;
        while ((diff & (diff - 1)) != 0) {
            diff = diff & (diff - 1);
        }
        return (diff << 1) - 1;
    }
}
```
