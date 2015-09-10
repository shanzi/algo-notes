# Password Attacker

Given the letters a password contains and the total length of it but not knowing the order
and the numbers of each letter, you are asked to give the number of all possibilities.

Note that each letter appears at least once. This is a dynamic programming problems.
Let $$DP[i][j]$$ denotes with the first $$i$$th letters, how many different ways we have for a password of
length $$j$$. Then we have iterating formular:

$$
DP[i + 1][j] = \sum_{k = i}^{j - 1} C_j^k \cdot DP[i][k]
$$

Where $$C_j^k$$ is the combination numbers of picking $$k$$ objects from $$j$$ different objects.

The key to pass the large test set is that we have to calcualte a large combination numbers such as
$$C_j^k$$ with modulo of a prime number `1000000007` correctly. As in the calculation of combination
numbers we need to do divide, so we can not just do modulo in each step. Instead we make use of a
proposition that for $$(x / y) % p$$ while $$p$$ is a prime number, there is a

$$
(x / y) % p = (x\cdot y^{p - 2}) % p
$$

We calucate $$y^{p - 2]$$ in ahead of time and get it directly when we calculate $$C_j^k$$.

!CODEFILE "../apac/2015/RoundB/PasswordAttacker.java"
