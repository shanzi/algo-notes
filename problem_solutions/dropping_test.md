# Dropping Tests

This is a typical problem to maximize the average value. There is a general solution to this kind
of problems.

Say we have series of values $$v_i$$ and $$w_i$$, we'd like to pick a subset $$S$$ from them and maximize
the the value of

$$
\frac{\sum_{i \in S} v_i}{\sum_{i \in s} w_i}
$$

Usually, we will restrict $$|S| \ge K$$.

There is no direct way to do this, but we can apply binary search method to guess a average value
and verify if it is possible to pick such an $$S$$ that let the value above is less than or equal to
our guessed value.

Assume our guessed value is $$R$$, we are going to verify if there is a subset $$S$$ that let
$$
\frac{\sum_{i \in S} v_i}{\sum_{i \in s} w_i} \le R
$$

That is:

$$
\sum_{i\in S}v_{i} \le R\sum_{i\in s}w_{i} \Rightarrow
\sum_{i\in S}v_{i} \le \sum_{i\in s}R\cdot w_{i} \Rightarrow
\sum_{i\in S}v_{i}-R\cdot w_{i}	\le	0
$$

So given a guessed $$R$$ and fixed count of elements $$K$$, we can sort $$v_i - R\cdot w_i$$ and
pick the first $$K$$ values to sum up to check if it is greater than zero. If it is greater than zero,
then there won't be other subset that is feasible with $$R$$ so we should decrease the guessed $$R$$,
or the guessed $$R$$ is feasible and we try increase it to find the edge between feasible and infeasible.
That value will be maximum possible average value. As we need resort the array for every guessed $$R$$,
The time cost is proportional to $$O(N\log^2 N)$$

Apply this strategy, we have the solution below. Note as for this kind of problems, the result should be decimal
and you should be careful with the calucation and search precision:

!CODEFILE "../poj/DroppingTests.java"
