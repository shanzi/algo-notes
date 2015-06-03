public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = nums[i];
            // Do not need to check k < i - 3. 
            for (int j = 2; i - j >= 0 && j <= 3; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + nums[i]);
            }
            max = Math.max(dp[i], max);
        }
        
        return max;
    }
}
