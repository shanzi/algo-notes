# X-Factor Chains (POJ 3421)

This turns out to be a hard question. The difficult exist in that not only the longest chain length
but also how many ways of longest chains is required to be returned. This is a problem related to prime factors
of a number.

Given the last number $$X_n$$, how will get produce longest X-Factors? Obviouly if we divide it with a
number $$a$$ but $$a = xy$$, we can always divide $$x$$ and $$y$$ separately to produce a longer chain,
so in the longest chain we should always dividing $$X_i$$ with a prime factor of $$X_n$$ to produce $$X_{i - 1}$$
and then $$X_{i - 2} \cdots X_1$$. So we first find prime factors $$p_1, p_2, \cdots, p_i$$
of $$X_n$$ and greedily pick the smallest $$i$$ ones such that $$p_1\cdot p_2\cdots p_i \le X_n$$.
Then we start from the $$p_1$$ to try dividing $$X_n$$ in case $$X_n$$ can be perfectly divided by some of $$p_i$$
many times. We greedily divide the smallest prime factors so that $$X_n$$ is divided to $$1$$ as slow as possible.
Then at last we can represent $$X_n$$ as

$$
X_n = p_1^{l_1}\cdot p_2^{l_2}\cdots p_i^{l_i}
$$

That is, start from $$X_0 = 1$$ , if one time we muliply $$X_{i - 1}$$ with one of the items(if the same item can be
multiplied many times, it is counted every time) to produce $$X_n$$, we will produce a seqence of $$X_i$$.
So the longest X-Fractor chains has length $$\sum l_i$$.

If we change the order of $$p_i$$ in producing $$X_n$$ from $$X_0$$, we will get different ways to produce X-Factor
chains with the same length (which is longest). We have the total ways of ordering to be:

$$
\frac{(\sum l_i)!}{l_1!\cdot l_2!\cdots l_i!}
$$

We divide $$(\sum l_i)!$$ with each $$l_i!$$ as if two of the same prime factors exchanged places, it will produce
the same X-Factor chains.

!CODEFILE "../poj/XFactorChains.java"
