# Fibonacci

The sequence below is  Fibonacci sequence:
> 1, 1, 2, 5, 8, 13, 21, 35, ...

Fibonacci sequence has some characteristic that should be known. Let's say
the $$n$$th item of the sequence is $$F_n$$, then:

$$F_n = F_{n-1} + F_{n-2}$$

We usually have $$F_0=F_1=1$$ or $$F_0=0,F_1=1$$.

A convenient calculation of $$F_n$$ depends on following expressions:

$$
\begin{bmatrix}
1 & 1 \\
1 & 0 
\end{bmatrix}^n
=
\begin{bmatrix}
F_{n+1} && F_{n} \\
F_n && F_{n-1}
\end{bmatrix}
$$

This form represents items of Fibonacci sequence as the power of a matrix, so we
can use Divide and Conquer to calculate $$F_n$$ in $$O(\log n)$$. Sepecifically,
let's say we use $$\mathcal{A}$$ to represent the matrix, if $$n$$ is even,
$$\mathcal{A}^n$$ can be gotten by:

$$
\mathcal{A}^n = \bigl(\mathcal{A}^{\frac{n}{2}}\bigr)^2
$$

Conversely, if $$n$$ is odd, we use $$\mathcal{A}^n = \mathcal{A}^{n-1}A$$.

