public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int[] withone = new int[nums.length - 1];
        withone[0] = nums[0];
        
        int max = nums[0]; // Use nums[0] instead of 0 !
        
        for (int i = 1; i < withone.length; i++) {
            int l = (i - 2 >= 0) ? withone[i - 2] : 0;
            int ll = (i - 3 >= 0) ? withone[i - 3] : 0;
            withone[i] = Math.max(l, ll) + nums[i];
            max = Math.max(max, withone[i]);
        }
        
        int[] withoutone = new int[nums.length];
        
        for (int i = 1; i < withoutone.length; i++) {
            int l = (i - 2 >= 0) ? withoutone[i - 2] : 0;
            int ll = (i - 3 >= 0) ? withoutone[i - 3] : 0;
            withoutone[i] = Math.max(l, ll) + nums[i];
            max = Math.max(max, withoutone[i]);
        }
        return max;
    }
}
