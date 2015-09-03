# Apple Catching

There are two apple trees, in each minute, one of the apple trees will drop an apple. You are given
the tree number that drops apples in each minutes and you can which tree you are standing under
to fetch the apple but the number of switching is limited. You are asked the maximum number of apples
you can get.

The key to this problem is apply dynamic programming. Let $$DP[i][j]$$ denotes the maximum number of apples
you can get after the $$i$$th minutes and you have switched $$j$$ times of position. Obviously when $$j % 2 == 0$$
you are in the first tree and otherwise you are in the second tree. So the iteration formular can be written as:

$$
DP[i][j]=\max\left\{ DP[i-1][j-1],DP[i-1][j]\right\} +\begin{cases}
1 & under same tree\\
0 & otherwise
\end{cases}
$$

Under same tree means the apple trees at the same tree as the tree you will be after $$j$$ times of switching.

!CODEFILE "../poj/AppleCatching.java"
