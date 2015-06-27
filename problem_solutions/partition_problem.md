# Partition Problem

Given an array of numbers, if there is a subset that the sum of the items in the subset equals
to exactly half of the sum of total array? This is a NP-Complete problem and similar to
knapsack problem, it has a pseudo-polynomial DP solution for integers when the problem's scale
is not too large.

Let $$DP[i][j]$$ denotes if there are a subset from the first $$j$$ elements which the sum
of items in the subset equals to $$i$$. If the sum of all items $$N$$ is even, then
$$DP[\frac{N}{2}][n]$$ is the solution where `n` is the number of all items.

It is obviously that $$DP[0][i] = true$$ holds for all $$i$$. As for $$DP[i][j]$$,
it is true if either $$DP[i][j - 1]$$ is true or $$DP[i - x_{j}][j - 1]$$ is true.
where $$x_{j}$$ is the $$j$$th element in the array.

Sample code:

```java
class PartitionProblem {
    public static boolean can partition(int[] array) {
        int sum = 0;
        int n = array.length;
        for (int x: array) {
            sum += x;
        }
        if (sum % 2 != 0) return false;
        boolean[][] DP = new boolean[sum / 2 + 1][n + 1];

        for (int i = 0; i <= n; i++) DP[0][i] = true;

        for (int i = 0; i <= sum / 2; i++) {
            for (int j = 0; j <= n; j++) {
                if (i >= array[j]) DP[i][j] = DP[i][j - 1] || DP[i - array[j]][j - 1];
                else DP[i][j] = DP[i][j - 1];
            }
        }

        return DP[sum / 2][array.length];
    }
}
```
