# Median of Two Sorted Arrays

To achieve a time complexity of $$O(\log M + N)$$ we need to apply methods similar to
binary search. After each iteration, we have to exclude half of elements from remaining items
in one of the arrays.

At first, find the median is equivalent to find `k`th elements in two arrays where
there are exactly `k` elements that is less than or equal to that number. If the total number of elements
is even, we return the average value of `k`th and `k+1`th element.

To find the range to exclude, let's assume `nums1[mid1]` is greater than `nums2[mid2]`,
let `left` be the number of elements on the left of `mid1` and `mid2` (except `nums1[mid1]`).
If `k <= left`, we can conclude that every elements `nums1[i]` with `i >= mid1` won't be
median as all those `nums1[i]` is greater than `nums1[mid1]` and the number of elements that are
less than or equal to `nums1[mid1]` is greater than `k`.

Conversely, if `k > left`, consider the range `nums2[l..mid2]`. All these elements is less than or
equal to `nums1[mid1]`, if median exists in this range, there must be `median <= nums1[mid1]`.
Then the number of elements that is less than or equal to `median` must be less than `k`,
which is a contradiction. So the position of `median` must not in that range, so we can exclude it.
Then in the range left we should find the `(k - mid2 + l2 - 1)`th number. 

The discussion is similar when `nums1[mid1] < nums2[mid2]`. Note that to implement the program correctly
we must make sure what `k` stands for is always number of elements less or equal to `median`.
That is `k >= 1`. When `k = 1` we should return the first element.


```java
public class MedianOfTwoSortedArrays {
    private int findKth(int k, int[] nums1, int[] nums2, int l1, int r1, int l2, int r2) {
        if (l1 > r1) return nums2[l2 + k - 1];
        if (l2 > r2) return nums1[l1 + k - 1];
        
        int mid1 = (l1 + r1) / 2;
        int mid2 = (l2 + r2) / 2;
        int left = mid1 + mid2 - l1 - l2 + 1;
        
        if (nums1[mid1] <= nums2[mid2]) {
            if (k <= left) {
                return findKth(k, nums1, nums2, l1, r1, l2, mid2 - 1);
            } else {
                return findKth(k - mid1 + l1 - 1, nums1, nums2, mid1 + 1, r1, l2, r2);
            }
        } else {
            if (k <= left) {
                return findKth(k, nums1, nums2, l1, mid1 - 1, l2, r2);
            } else {
                return findKth(k - mid2 + l2 - 1, nums1, nums2, l1, r1, mid2 + 1, r2);
            }
        }
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int k = (m + n + 1) / 2;
        
        int kth = findKth(k, nums1, nums2, 0, m - 1, 0, n - 1);
        if (((m + n) & 1) == 1) return (double)kth;
        else {
            int kth2 = findKth(k + 1, nums1, nums2, 0, m - 1, 0, n - 1);
            return ((long)kth + (long)kth2) * 0.5;
        }
    }
}
```
