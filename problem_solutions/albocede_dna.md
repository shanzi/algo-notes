# Albocede DNA

This is a hard DP problem. The key is to find the iteration formular. Let's first consider a simpler problem
in which we only need to guarantee that the subsequence is in a form of `aaabbbcccddd`. In other words,
each letter appears more than once and they are in a order of `abcd`.

As for the simpler problem. We use $$DP[i][j]$$ denotes the count of subsequence in string `str[0..i]` that
ends in letter `j`. So we have:

1. if `str[i] == 'a'`, $$DP[i][0] = DP[i - 1][0]\times 2 + 1.$$
2. if `str[i] == 'b'`, $$DP[i][1] = DP[i - 1][1] + DP[i - 1][0].$$
2. if `str[i] == 'c'`, $$DP[i][2] = DP[i - 1][2] + DP[i - 1][1].$$
2. if `str[i] == 'd'`, $$DP[i][3] = DP[i - 1][3] + DP[i - 1][2].$$

The result is $$DP[n][3]$$.

As for our original problem. We have to seperate the cases of $$DP[i][j]$$ to several cases to enable
controlling of letter count. Let $$DP[i][j][k][c]$$ means the count of valid subsequence ends with
letter $$c$$ and the number of `a`s minus number of `c`s is $$j$$ and the number of `b`s minus the
number of `d`s is $$k$$. As all `a`s is in front of `c`s and all `b`s is in front of `d`s, there is
no point to record the cases that `j < 0` and `k < 0` as we won't start from those states to produce
a valid subsequence.

After reduced the space uses, we can get a solution like:

!CODEFILE "../apac/2016/RoundB/AlbocedeDNA.java"
