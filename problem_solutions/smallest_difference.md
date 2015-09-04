# Smallest Difference (POJ 2718)

This is a hard problem. To run it within time limits, we have to take some manners to improve
the time efficiency. At first, the minimum difference occurs when two numbers have near or same
number of digits. When difference of two integers' numbers of digits is too large, it won't contribute
to the smallest difference. So we first iterate all subset of $$\frac{N}{2}$$ and then we permute
all permutations of this subset and its complemental subset and calculate the difference.
In this procedure, we need to skip cases that the number starts with zero.

We you binary bits way to iterate all subset of size $$k = \frac{N}{2}$$ and we need to implement
next permutation function correctly. The next permutation should return `true` if there is a next
permutation and return `false` if it is already the lexicographic largest permutation. Before
return `false`, it must reverse the sequence to restore it to the lexicographic smallest permuation.

!CODEFILE "../poj/SmallestDifference.java"
