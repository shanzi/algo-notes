# Sumsets (POJ 2229)

This problem is the same as counting how many ways there is to sum up to some money value by
given coins face values and count. The data range in this problem is not large, so we can come up
a $$O(N^2)$$ problem. As each number can be used unlimited times, we can give the following recursion
formular:

$$
DP[i] = \sum_{j = 1}^{j\times j \le i} DP[i - j\times j], 
$$

Where $$DP[i]$$ is the number of ways to sum to $$i$$, our final answer will be $$DP[N]$$.

!CODEFILE "../poj/Sumsets.java"
