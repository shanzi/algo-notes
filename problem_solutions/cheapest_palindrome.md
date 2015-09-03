# Cheapest Palindrome (POJ 3280)

This is quite a problem of high quality. Given a piece of string, use are asked to return
the minium cost to turn that string into a palindrome string. You can add or remove characters
in a given set with different cost.

You actually need not find the final palindrome string first and then calculate the cost.
That will be too hard. On contrary we use dynamic programming method to find the minimum cost
direactly.

Let $$DP[i][j]$$ be the least cost to turn $$s[i..j]$$ into palindrome. Obviously we have $$DP[i][i] = 0$$. 
As to turn $$s[i..j]$$ to palindrome string, we can at first turn $$s[i+1..j-1]$$ to palindrome and if $$s[i] = s[j]$$
then we need not do anything to make $$s[i..j]$$ to palindrome, or we have to modify the string
which have several possible cases:

1. We can first make $$s[i+1..j]$$ to palindrome and add $$s[i]$$ after $$j$$ or remove $$s[i]$$
2. We can first make $$s[i..j-1]$$ to palindrome and add $$s[j]$$ before $$i$$ or remove $$s[j]$$

In both cases we can either add a character or remove a character, to add or remove is up to
which operation of corresponding character is cheaper. So the iteration formular will be:

$$
DP[i][j]=\begin{cases}
DP[i+1][j-1] & s[i]=s[j]\\
\max\left\{ DP[i+1][j]+cost(s[i]),DP[i][j-1]+cost(s[j])\right\}  & otherwise
\end{cases}
$$

!CODEFILE "../poj/CheapestPalindrome.java"
