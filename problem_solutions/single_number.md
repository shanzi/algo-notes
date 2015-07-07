# Single Number

Given a series of integers, each of the elements in the array appears $$k$$ times except one,
you are asked to return that value.

When $$k = 2$$ the problem is a quite simple one. As we know for any integer $$n$$,
$$n ^ n = 0$$ and $$0 ^ n = n$$. So we `XOR` all elements in the array, as every elements
that appears twice will product $$0$$ as the two operand of `XOR` operator can be exchanged.
Then the results we be the same. This solutions is also correct when $$k$$ is even and
the only one item in the array appears odd times.

So what if $$k$$ is even? For the simplest case let's consider $$k=3$$. In this case
we can not simply apply our `XOR` all elements solution, but let's consider a single bit position `i` and
the corresponding position in the number we want is `1`. If we count the number of `1`s appears at this position
through all elements, we will find it is a number that is not divisible by `3`. This conclusion comes from
the following observasions:

1. The number to find has `1` at this position, so it gives 1 or 2 count
2. As for one of other numbers, if it has `1` at this position, as it appears three times,
it always contributes 3 to the count.
3. If one of other numbers has `0` at this position, it won't change the result.

On the contrary, if the number to find has `0` at this position, the count of `1`s at that position
will be divisible by `3` too.

The simplest way to implement this is to use an array to count each position's `1` bits.
A better way is to use bit manipulation and turns it into a dyanamic programing like solution.
Let's say `dp[i][0]`, `dp[i][1]` and `dp[i][2]` represents the states of elements from `0` to `i`.
In `dp[i][0]`'s each bit position, if the number of `1`s appears there `MOD` 3 is `0`, then that
bit position is `1`. It is the same for the latter two variable. Then given `i + 1`, it is obviously that

{% math %}
\begin{eqnarray*}
dp[i+1][0] & = & (dp[i][2]\&n[i+1])|(dp[i][0]\&\sim n[i+1])\\
dp[i+1][1] & = & (dp[i][0]\&n[i+1])|(dp[i][1]\&\sim n[i+1])\\
dp[i+1][2] & = & (dp[i][1]\&n[i+1])|(dp[i][2]\&\sim n[i+1])
\end{eqnarray*}
{% endmath %}

At last we can return `~dp[n][0]`.
To optimized the space use, we can just use 4 variable to implement this solution:

```java
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int mod0 = -1;
        int mod1 = 0;
        int mod2 = 0;
        int t;
        
        for (int n : nums) {
            t = mod2;
            mod2 = (mod1 & n) | (mod2 & ~n);
            mod1 = (mod0 & n) | (mod1 & ~n);
            mod0 = (t & n) | (mod0 & ~n);
        }
        
        return ~mod0;
    }
}
```

Start from this, we can also write out general code for $$k\ge 3$$. There is a solution
using less variables given in a
[discussion](https://leetcode.com/discuss/43377/the-simplest-solution-ever-with-clear-explanation)
on LeetCode about this question.
