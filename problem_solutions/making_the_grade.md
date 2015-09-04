# Making the Grade (POJ 3666)

At first, when we add height or remove height at a point, we should always make it to a existing
height in current grade. There is no need to doing more or less. Based on this we can give a DP solution.

Let's say the original array is $$H$$, at first we sort the current heights into an array $$S$$.
Let $$DP[i][j]$$ be that the minimum cost with the first $$i$$ items in $$H$$ and we make the slope
ends in a height of $$H[j]$$. Then we have our DP formular:

$$
DP[i + 1][j] = \min_{\forall j}\left\{DP[i][j]\right\} + |S[i] - H[i]|
$$

We can same the minimum $$DP[i][j]$$ during iteration to reduce running time. With this solution
we need not care if the slope is going up or down.

!CODEFILE "../poj/MakingTheGrade.java"
