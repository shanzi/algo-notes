# Search In Rotated Sorted Array

If elements in the array are distinct, we can easily decide which half the the target will be.

```java
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid;
        
        while (l <= r) {
            mid = (l + r) / 2;
            
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                if (nums[l] <= nums[mid] && nums[l] > target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                if (nums[r] >= nums[mid] && nums[r] < target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        
        return -1;
    }
}
```

If there are repleated elements, it is a little harder to decide which half to advance our program to
and sometimes we may have to search on both halves. So at first we find the conditions that can narrow
next search range to one half. If the condition not holds, we search both parts.

```java
public class SearchInRotatedSortedArray {
    private boolean search(int[] nums, int target, int l, int r) {
        if (l > r) return false;
        
        int mid = (l + r) / 2;
        
        if (nums[mid] == target) return true;
        else if (nums[mid] < target) {
            if ((nums[r] > nums[mid] && nums[r] < target) || (nums[l] > nums[mid] && nums[l] <= target)) {
                return search(nums, target, l, mid - 1);
            } else if (nums[l] < nums[mid] || nums[r] >= target) {
                return search(nums, target, mid + 1, r);
            } else {
                return search(nums, target, mid + 1, r) || search(nums, target, l, mid - 1);
            }
        } else {
            if ((nums[l] < nums[mid] && nums[l] > target) || (nums[r] < nums[mid] && nums[r] >= target)) {
                return search(nums, target, mid + 1, r);
            } else if (nums[r] > nums[mid] || nums[l] <= target) {
                return search(nums, target, l, mid - 1);
            } else {
                return search(nums, target, l, mid - 1) || search(nums, target, mid + 1, r);
            }
        }
    }
    
    public boolean search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }
}
```
