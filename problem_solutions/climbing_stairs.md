# Climbing Stairs

This problem is actaully asking you to generate the Fibonacci sequence.
According to the [problem statement](https://leetcode.com/problems/climbing-stairs/).
When you are getting to the $$n$$th stair. You can at first get to the $$n - 1$$th stair
and climb 1 stair to met the top, or, you can at first get to the $$n - 2$$th stair
and then climb 2 stairs to the top. So the total ways to get the $$n$$th stair
equals to the sum of ways to get $$n - 1$$th and $$n - 2$$th stair. If we regard
this problem as a Dynamic Programing problem, we have

{% math %}
DP[i] = DP[i - 1] + DP[i - 2]
{% endmath %}

We have $$DP[0] = DP[1] = 1$$, so it will be a Fibonacci sequence generated for $$DP[i}$$.

```java
public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n < 2) return 1;
        
        int a = 1;
        int b = 1;
        int c = 1;
        
        for (int i = 2; i <= n; i++) {
            c = b;
            b = a + b;
            a = c;
        }
        
        return b;
    }
}
```
