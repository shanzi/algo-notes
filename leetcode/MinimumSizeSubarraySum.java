public class MinimumSizeSubarraySum {
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
