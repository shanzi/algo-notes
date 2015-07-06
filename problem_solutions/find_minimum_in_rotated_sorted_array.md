# Find Minimum in Rotated Sorted Array

This problem is a series problems that have a little difficulty. The main difficulty not
only exists in thinking of the idea to solve it, but also in implementing it correctly with out bug.
The solution to this series of problems makes use of the same idea as binary search, so it
is very important to handle search boundary correctly to avoid infinite looping and wrong answer.

## No duplicated elements

If there is no duplicated elements in the array, the program will be much like that of binary search.
In this case, we have only one minimal element $$i$$. At first we pick a boundary $$l$$ and $$r$$ and
make sure $$l \le i \le r$$. Let $$mid = \left\lfloor \frac{l+r}{2}\right\rfloor$$, if $$nums[mid] < nums[r]$$,
we know that $$i \le mid$$, or there must have $$i > mid$$. To avoid infinite looping,
we have to keep:

1. After each iteration, we still have $$l \le i \le r$$.
2. After each iteration, $$l - r$$ is less than before

We let $$r = mid$$ when $$nums[mid] < nums[r]$$ and let $$l = mid + 1$$ when otherwise.
As there always be $$l \ge mid < r$$ when $$l < r$$, so $$l - r$$ will become less and less
and the loop must have an end.

```java
public class FindMiniumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        
        while (l < r) {
            mid = (l + r) / 2;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        
        return nums[l];
    }
}
```

## Allow duplicated elements

If duplicated elements is allowed, the problem becomes a little more complex and we cannot
implement it without recursion. The reason is that, if $$nums[l] = nums[mid] = nums[r]$$,
the minimal number may appears both in $$[l, mid]$$ or $$(mid, r]$$. The cases is also a little
more difficult to discuss. we disscuss all cases below:

1. $$nums[mid] < nums[r]$$: the same as before, the minimal element must be in $$[l, mid]$$
2. $$nums[mid] > nums[r]$$: the same as before, the minimal element must be in $$(mid, r]$$
3. $$nums[mid] = nums[r]$$: there are several different cases respect to different relationship between
$$nums[mid]$$ and $$nums[l]$$
    1. $$nums[mid] = nums[l]$$: both $$[l, mid]$$ and $$(mid, r]$$ is possible.
    2. $$nums[mid] < nums[l]$$: must be in $$[l, mid]$$
    3. $$nums[mid] > nums[l]$$: must be in $$[l, mid]$$

We find that the only difference comes when $$nums[l] = nums[mid] = nums[r]$$, and if $$mid[l] \neq mid[mid]$$,
we should put union $$nums[mid] = nums[r]$$ into the case of $$nums[mid] < nums[r]$$. So the solution is:

```java
public class FindMiniumInRotatedSortedArray {
    private int findMin(int[] nums, int l, int r) {
        if (l >= r) return nums[l];
        
        int mid = (l + r) / 2;
        
        if (nums[mid] == nums[r] && nums[mid] == nums[l]) {
            return Math.min(findMin(nums, l, mid), findMin(nums, mid + 1, r));
        } else if (nums[mid] <= nums[r]) {
            return findMin(nums, l, mid);
        } else {
            return findMin(nums, mid + 1, r);
        }
    }
    
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
}
```
