# Power of Two

Power of two is a simple question. Given an integer, you are asked to decide if
it is a power of two. Obviously, an integer has only one 1 in its binary representation
is a power of two.

But, there is a trap with this question. Let's start from a seems correct solution:

```java
boolean isPowerOfTwo(int n) {
    return (n & -n) == n;
}
```

This solution is smart. `(n & -n)` is the integer with only one bit is 1 and that bit is at
the position of the first 1 in `n`'s binary representation. And if it equals to `n` itself,
then `n` only have one bit is 1, so it is a power of two. Sounds good, isn't it?
But what about `n = 0`? It will returns true after all. However, zero is not a power of 2
(BTW, 1 is a power of 2, it's $$2^0$$). So an improved solution might be:

```java
boolean isPowerOfTwo(int n) {
    if (n == 0) return false;
    return ((n & -n) == n);
}
```

Well, this solution is very nearly to corrent. But there is one thing left.
What if the number is the minimal number of integer?
It is `Integer.MIN_VALUE` in java and in its binary representation the first 1
appears at the highest bit. But negative integers can not be a power of a (positive) two, aren't they.
So at last, we get the final correct answer:

```java
boolean isPowerOfTwo(int n) {
    return (n > 0) && ((n & -n) == n);
}
```

There is yet another way. We use `(n & (n - 1)) == 0` instead of `(n & -n) == n`.
This works when `n` is a unsigned integer. But `n = 0` must be handled separately.
`(n & (n - 1))` returns a integer that except for the 1 at the lowest bit position,
all bits will be the same as `n`. So if it returns 0, the number only has one 1 bit.
The code is:

```java
boolean isPowerOfTwo(int n) {
    return (n > 0) && ((n & (n - 1)) == 0);
}
```
