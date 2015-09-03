# Coins (POJ 1742)

Given a series of coins with their face values and count, you are asked to return how many prices under $$m$$
can them sum to. Let $$DP[i][j]$$ denotes if price $$j$$ can be sumed up to with the first $$i$$ types of coins.
At first we should short the coins according to their face value, then we can use the iteration formular:

$$
DP[i][j]=\begin{cases}
true & DP[i-1][j-k\times v_{i}],k\in[0,c_{i}]\\
false & otherwise
\end{cases}
$$

Here $$c_i$$ is the count the the $$i$$th type of coin and $$v_i$$ is its face value.

This formular cost $$O(N^2)$$ time which is not feasible. We have to optimize the formular.
Obviously if $$DP[i - 1][j - (k + 1)\cdot x]$$ is able to be achieved, then $$DP[i - 1][j - k\cdot x]$$
must be able to be achieved and at last we have $$DP[i - 1][j - x]$$ is able to be achieved.
So we have $$DP[i][j - x]$$ is able to be achieved. So every time we just need to check if $$DP[i][j]$$
can be achieved within the count of the $$i$$th type of coins. So the time cost can be reduced to $$O(N)$$.

!CODEFILE "../poj/Coins.java"
