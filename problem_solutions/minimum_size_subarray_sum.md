# Minimum Size Subarray Sum

Using a slide window. For each `i` we can find the shortest subarray that the sum of elements
is greater than `s`. Instead of enumerate all subarray, we using a slide window and a variable `sum`
to keep track of the partial sum.

```java
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int l = -1;
        int min = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                min = Math.min(min, i - l);
                sum -= nums[++l];
            }
        }
        if (min > nums.length) return 0;
        else return min;
    }
}
```

This solution is correct based on the following observasion:

1. if for `i`, the shortest subarray starts from `l`, then if there exists such a subarray
ends at `i + 1` and shorter than that for `i`, then the subarray must start after `l`.
2. if the sum of all elements in the array is less than `s`, `min` will never be modified.
So if at last `min > nums.length` we should return 0.
