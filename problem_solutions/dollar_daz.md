# Dollar Dayz (POJ 3181)

A simple problem, it is in fact equal to how many ways we can use several type of coins to spend
a given sum of money. Every coins has unlimited numbers.

Our original DP formular is:

$$
DP[i][j] = \sum_{k = 0}^{v_i\cdot k \le j} DP[i - 1][j - k\cdot v_i]
$$

While $$DP[i][j]$$ is the number of ways to spend $$j$$ with first $$i$$ types of coin.
But we can do it better to reduction the formular to:

$$
DP[i][j] = DP[i][j - v_i]
$$

!CODEFILE "../poj/DollarDayz.java"
