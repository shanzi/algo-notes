# Card Game

Given series of card and a number `K`, if there are three consecutive cards `a`, `b` and `c` conforms
that `a + K = b` and `b + K = c`, we can drop the three cards off. Then after serveral round of dropping,
what's the least possible number of cards remains (which can not be drop any more).

This is a dynamic programming problem. Let $$DP[i][j]$$ is the minimum number of cards remains after dropping cards
in `str[i..j]` (`i`, `j` inclusive), then we can write the following iterate formular:

$$
DP[i][j]=\min_{i\le k<j}\left\{ DP[i][k]+DP[k+1][j]\right\} 
$$

What's more, when `cards[i] + K = cards[k]` and `cards[k] + K = cards[j]` as well as $$DP[i+1][k-1] = DP[k+1][j-1] = 0$$
we have $$DP[i][j] = 0$$.


We can compress the space cost of `DP` matrix, but by doing so, the iteration order to calculate the values is very
important and should be handled with attention. 

!CODEFILE "../apac/2015/RoundB/CardGame.java"
