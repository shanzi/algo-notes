# Backward Digit Sums (POJ 3187)

The basic idea is that we generate every possible permutations of length $$K$$ from `0` to `9` as the first line
and quickly calculate the sum. Let's say $$K = n$$ so the items in the first line is $$a_0, a_1, \cdots, a_{n-1}$$.
Then the value of the final sum at the bottom end of the triangle will be:

$$
\sum_{k=0}^{n-1}\left(\begin{array}{c}
k\\
n-1
\end{array}\right)a_{k}
 
$$

where $$\left(\begin{array}{c} k\\ n-1 \end{array}\right)$$ means the number of combinations to pick $$k$$ objects
from $$n - 1$$ different objects.

!CODEFILE "../poj/BackwardDigitSums.java"
