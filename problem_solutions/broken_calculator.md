# Broken Calculator

Given a calculator with some of the buttons broken and can only perform multiply operation,
you are asked to return the minimum number of clicks you need to perform. This is a typical dynamic programming
problem. Let $$DP[n]$$ is the minimum number of clicks to achieve $$n$$, then we have:

$$
DP[n] = \min \{DP[k] + DP[n/k] + 1\} 
$$

Where $$n$$ can be perfectly divided by $$k$$. This formular is good enough for small case,
as to pass the large test cases which $$n$$ can be $$10^6$$, we have to optimize the time cost.
An obvious way is to apply similar techniques of prime sieve algorithm. If it is possible
to achieve $$i$$, then when we meet $$DP[i]$$, we can update $$DP[i\cdot 2], DP[i\cdot 3],\cdots, DP[i\cdot j]$$
where $$i\cdot j\le n$$. The time cost after this optimization will be proportional to $$n$$,
so the problem becomes feasible.

!CODEFILE "../apac/2015/RoundC/BrokenCalculator.java"
