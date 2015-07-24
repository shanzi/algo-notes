# First Missing Positive

We put int `i` to the `i`th position one by one, skip integers in the array that is less than zero or
greater or equal to the array length. At last from the first position we check if `nums[i] == i` and
return the first unmatch. If there isn't an unmatch in `nums[1..end]`, we check `nums[0]`.
If it is `nums.length`, we return `nums.length + 1`, or we return `nums.length`.

```java
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        
        if (nums.length == 0) return 1;
        
        int temp;
        
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] < 0 || nums[i] >= nums.length) break;
                if (nums[i] == nums[nums[i]]) break;
                
                temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        
        if (nums[0] == nums.length) return nums.length + 1;
        else return nums.length;
        
    }
}
```
