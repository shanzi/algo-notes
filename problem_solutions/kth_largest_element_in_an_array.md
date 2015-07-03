# Kth Largest Element in an Array

This is a classic problem and has a optimized $$O(N)$$ in average solution with
divide and conquer. Finding the Kth largest element is equivalent to finding the
(N - K + 1)th smallest element. Thus the index of this element after sort is
(N - K). This solution in the worst case will have a time cost of $$O(N^2)$$.

```java
public class KthLargestElementInAnArray {
    private int partition(int[] nums, int l, int r) {
        // This is a very very important function, remember the code
        int p = nums[l];
        int m = r + 1;
        int j = r + 1;
        
        do {
            while (nums[--j] < p);
            swap(nums, j, --m);
        } while (j > l);
        
        return m;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j]
        nums[j] = t;
    }
    
    private int findKthLargest(int[] nums, int l, int r, int k) {
        int m = partition(nums, l, r);
        if (m == k) return nums[m];
        else if (m < k) return findKthLargest(nums, m + 1, r, k);
        else return findKthLargest(nums, l, m - 1, k);
    }
    
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }
}
```

Find the median is a similar problem, but please note that if count of elements is even ,
the median is the average of `nums[N/2]` and `nums[N/2 + 1]`.
