# Arrange the Bulls (POJ 2441)

We use state compressed dynamic programming method to solve this problem. To compress the barn occupying
state, we use an `int` integer in which a bit position is `1` if that position is free. Then we start
from the first cow and every time we get the number of ways to arrange the first `i` cows. When it
comes the next cow, we try to calculate the total number of ways for adding this cow into previous state.

Let $$DP[i][state]$$ stands we the number of ways for every barn occupying state if have arranged `i` cows.
Then:

{% math %}
DP[i + 1][a] = \sum_{a \& b \neq 0} DP[i][b]
{% endmath %}

Where `b` stands for every state representing that if we put this cow to one of its desired barn.
Note that as to pass the test we have to reduced the dynamic programming matrix to two rows when implementing
the solution.

!CODEFILE "../poj/ArrangeTheBulls.java"
