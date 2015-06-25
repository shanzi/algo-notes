# House Robber

This is a series problems: [House Robber](https://leetcode.com/problems/house-robber/) and
[House Robber II](https://leetcode.com/problems/house-robber-ii/).

The former problem is a simplest Dynamic Programming problem. At index `i`, the maximum
rob earning value comes from `i - 2` and `i - 3`. Houses numbered below `i - 3` has no
effect on `i` as each of them can either jumps to `i - 2` or `i - 3` to get a higher learning.
So the DP iterating formular is:

{% math %}
DP[i] = \max \{DP[i - 2], DP[i - 3]\} + nums[i]
{% endmath %}

We finally return the max value among all $$DP[i]$$.

Sample code:

!CODEFILE "../leetcode/HouseRobber.java"

In the second problem, the houses are arranged in a circle. The simplest way to solve this
problem is cut the circle at the position between `n - 1` and `0` and reuse the method
used in the first problem. We need to apply the method twice, one with the first house
robbed and one without. Then we pick the maximum income between two cases.

!CODEFILE "../leetcode/HouseRobberII.java"
