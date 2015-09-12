# CubeIV

Given a matrix with each cell holds an integer number while all numbers are distinct.
As for a cell with number `i`, you can go from here to adjoined cells with number `i+1`.
The question is starting from which number we can go farest.

In fact we can put all these numbers from `1` to `N` in an one dimensional array and from
the matrix we can decide if we can go from `i` to `i + 1`(We let `next[i] = true` if so).
Then we use dynamic programming method and from the largest number `N` to `1`
 we record the longest steps we can go from `i` in $$DP[i]$$.  So we have:

$$
DP[i]=\begin{cases}
DP[i+1] & next[i]=true\\
1 & otherwise
\end{cases}
$$

!CODEFILE "../apac/2015/RoundD/CubeIV.java"
