public class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length || k < 0) return 0;
        return findKthLargest(nums, 0, nums.length - 1, k);
    }
    
    private int findKthLargest(int[] nums, int left, int right, int k) {
        int p = arrangeArray(nums, left, right);
        if (p == k - 1) return nums[p];
        else if (p > k - 1) return findKthLargest(nums, left, p - 1, k);
        else return findKthLargest(nums, p + 1, right, k);
    }
    
    private int arrangeArray(int[] nums, int left, int right) {
        int t = nums[left];
        int m = right + 1;
        int i = right + 1;
        do {
            while(nums[--i] > t);
            swap(nums, --m, i);
        } while (i > left);
        
        return m;
    }
    
    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
