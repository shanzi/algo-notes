# Gas Station

A brute force solution to this problem is that we try to start at every position and test if
we can finish the whole travel. But there is obviously a better linear solution. At first
we have the following observations:

1. If we start at `i` and after traveled to `j` we find we can not move forward any more,
then we can not start at $$k\in (i, j)$$ either. Thus we should try next from $$j + 1$$ instead
of $$i + 1$$.
2. If we can start at `i` and travel back in a circuit, we must be able to travel from `i`
the end of array.
3. If we find the first position one can travel to the end of array at `i` and gas left is `psum`,
we can then travel back to `i - 1` from the start of array if and only if `psum >= (psum - sum)`,
where `sum` is the total gas remain after visited every station and `psum - sum` stands for
gas needed to travel throught from `0` to `i - 1`. `psum - sum` must be a positive number.
4. We find that `psum >= psum - sum` is equivalent to `sum >= 0`. That is, if the total gas
remain after visited every station, we can always travels back, so the problem becames find the
first `i` that can let you travels to the end of array.


Code based on this solution:

```java
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int total = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
        }
        if (total >= 0) return start;
        return -1;
    }
}
```
