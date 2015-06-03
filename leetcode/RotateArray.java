public class RotateArray {
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }
    
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) return;
        // IMPORTANT: this is rotating right (NOT LEFT)!
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }
}
