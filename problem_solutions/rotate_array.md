# Rotate Array

This is a classical problem. The best solution is of time cost $$O(N)$$ and space cost $$O(1)$$.
In this problem we rotate the array to the right but we can also rotate an array to the left
using the same method with just a little modification.

The method concerns about reverse partial or whole array three times. Note that your code
must handle cases that `k` is greater than the array's length. Sample code:

```java
public class RotateArray {
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        if (k == 0) return;
        
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }
}
```
