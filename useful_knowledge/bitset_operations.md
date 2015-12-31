# Bitset Operations

We present bitset operations via the following problem: given a set of elements, generate all possible subset combination. 

If the number of elements is small, let's say less than 32, we can use an `int` value to represent a subset combination; to iterate through all the subsets of them. Let's say the whole set has $$n$$ elements.
We start from $$0$$ which stands for empty set, and each time we add one to the integer,
we get a new subset where bit $$1$$ in the binary representation stands for the corresponding
element are in the subset.

Apart from this, there are several other techniques can be used to perform some set operations.
Let the integer be $$s$$, here are some common usages:

1. Empty set $$\emptyset$$: `s=0`
2. Set contains only the $$i$$th element($${i}$$): `1 << i`
3. Universal set(set that contains all elements): `(1 << n) - 1`
4. Judge if the $$i$$th element is in the set: `(s >> i) & 1` or `s & (1 << i)`
5. Add the $$i$$th element to the set: `s | (1 << i)`
6. Drop the $$i$$th element in the set: `s & ~(1 << i)`
7. The union of two set $$s\cup t$$: `s | t`
8. The intersection of two set $$s\cap t$$: `s & t`

To iterate through all subsets of a set of $$n$$ elements:

```java
for (int s = 0; s < (1 << n); s++) {
    // handling subset
}
```

A type of questions is to iterate a subset's all subsets. Let's say there is a given subset represented with integer,
`s = 0x10101101`, how to get all the subsets of it based on this integer? One solution is we count the number of
elements in this subset and get all subsets of a set with equal elements count, then we transform the subsets
of the later to those of the former. This is a solution, but it is not time efficient.

A better solution is that we iterate from the whole subset `s` other than `0`. Each time we minus one from `s`
and get a new subset number stands for a set $$s'$$, but $$s'$$ may not be the subset of `s`, so we must
perform an intersection operation on it. An example implementation shows this idea:

```java
for (int subset = s; subset >=0; subset = s & (subset - 1)) {
    // handling subset
}
```

Notes, each iteration of the program will generate a different subset of `s`. This conclusion is drawn from
the following two observation:

1. The set generated in each iteration must be a subset of `s` as we have performed the intersection
operation `&`.
2. Each set generated is different. It is because for each subset, only the bits of pre-sensed elements
in the set are $$1$$, so once you minus $$1$$ from the integer, one of those bits must have changed.
So even after the intersection operation, some bits left are different. As the process will always
generate an integer smaller, so all the subsets won't repeat.

Then, a more complex case will be introduced. That is to generate all subsets with $$k$$ elements from
$$n$$ elements. One obvious solution is that we generate all subsets and pick sets with $$k$$ elements out.
But it will be too costly in time. A better but hard to get solution is:

```java
int comb = (1 << k) - 1;
while (comb < (1 << n)) {
    int x = comb & -comb;
    int y = comb + x;
    comb = ((comb & -y) / x >> 1) | y;
}
```

The idea of this solution is simple:

1. At first we pick the subsets with the smallest integer representation.
2. Each time we generate the smallest integer with the same number of $$1$$s larger than current one.
Thus we get the next subset with $$k$$ elements.
3. Repeat the steps until we it is larger than `1 << n`.

To generate the next integer with the same number of $$1$$s in binary representation needs the following steps:

1. Find the first continuous range of $$1$$s from the low bits
2. Set the first zero beyond those $$1$$s to $$1$$. At this time, the number of $$1$$s increased by one.
3. Shift the range of $$1$$s in step 1 to lower bits until the number of the $$1$$s decreased by one.
4. Then we get the smallest integer that is larger than the old one that with the same number of $$1$$.

The code above implements these steps but it might be quite difficult to understand. We will explain it
line by line in detail.

At first, we initialize the first set with `int comb = (1 << k) - 1;`. It is easy to understand this will
be the smallest integer with $$k$$ one in its binary representation.

Then in the loop, the first line we get the start of the first continuous range if $$1$$s. That is because,
in generic computer system, negative number can be get by reverse all binary bits and add one. In other words,
`-comp = (~comp) + 1`. After `~comp` every zero on the right of the first continuous range of $$1$$ will all
be $$1$$s now, after added 1, the first bit of that range will become $$1$$ while the others will be zero.
As the bits on the left of that range won't be affected and it has been reversed, so after `&` operation,
they will all become zero.

Then, what if we add the result `x` with the original `comp`? As in `x`, only the lowest bit of that first
range is $$1$$, so after the addition, the first range of ones will become zero and the first zero before
that range will become $$1$$. We finished half of the second step. 

We need to get the continuous range now, that's what `comb & ~y` does. As `y` and `comb` are all the same
on the left of that range expect the first bit. So after `&` operation with `~y` all bits on the left
will become zero including the different bit as in `comb` it is zero. Then, from the top bit of that range
to the right, all bits will keep unchanged. So we get the first continuous range of $$1$$ in `comp`.

To shift this range to the right until there are no zero at the lower bits of it, we just need to divide
the range by `x`. `x` has only the lowest bit of the range is $$1$$ remember it? At last, to decreased the
number of ones in the range by one, we shift it right once more.

After combined with `y`, we finally get what we want.

Bit operation related solution is a little hard to think of. More hard to get the optimized answers.
It's a little like solving puzzles. Just practice and get yourself familiar with these problems.
The number of types of similar problems is in fact limited. It is possible to solve most of those
problems with experiences.
