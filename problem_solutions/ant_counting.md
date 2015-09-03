# Ant Counting (POJ 3046)

This problem is equal to given several types of objects and their count and ask how many
different combinations you can get by picking `K` out of them.

Let $$DP[i][j]$$ denotes how many ways we can pick $$j$$ objects from the first $$i$$ types of objects.
Then we have:

$$
DP[i+1][j] = \sum_{k = 0}^{\min(j, c_i)} DP[i][j - k]
$$

But this formular cost $$O(NC)$$ time to process which is to cost. We have to optimize the formular to

$$
DP[i+1][j]=\begin{cases}
DP[i+1][j-1]+DP[i][j]+DP[i][j-1-c_{i}] & \; j-1-c_{i}\ge0\\
DP[i+1][j-1]+DP[i][j] & otherwise
\end{cases}
$$

Then the time cost reduced to $$O(N)$$ and is acceptable now.

!CODEFILE "../poj/AntCounting.java"
