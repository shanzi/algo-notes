# Space Elevator

At first we sort different type of blocks by their maximum altitude allowed. Then we apply the following
DP formular:

$$
DP[i][j] = \sum_{k = 0}^{k \le c_i} DP[i - 1][j - k\times l_i]
$$

Where $$c_i$$ is the count of blocks of the $$i$$th type of blocks. $$l_i$$ is their length.
$$DP[i][j]$$ denotes the maximum number of ways to achieve height $$j$$ with first $$i$$ types of block.

To apply the restriction of altitude, for each type of blocks we only check height $$j \le a_i$$ where
$$a_i$$ is the highest altitude allowed. Also we have to take manners to reduce the time and space complexity.

!CODEFILE "../poj/SpaceElevator.java"
