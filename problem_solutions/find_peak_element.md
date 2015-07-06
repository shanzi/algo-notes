# Find Peak Element

With $$nums[-1] = nums[n] = -\infty$$, find an index let $$nums[i - 1] < nums[i]$$ and $$nums[i + 1] < nums[i]$$.
Note that for any $$i$$, $$nums[i] \neq nums[i + 1]$$. A linear solution is simple, but we can solve this
problem in $$O(\log N)$$. Make use of the conditions that $$nums[i] \neq nums[i + 1]$$, we take a iteration
way that is very similar to binary search. For an index $$i$$, we search peak element in the left
side of it if $$nums[i - 1] > nums[i]$$, conversely, we search peak element in the right side.

To prove this algorithm is simple. If $$nums[i - 1] > nums[i]$$, as each adjoined element is different,
there must be a $$-1 \le j < i - 1$$ that let $$nums[j] < nums[i]$$, so there must be a peek element
in the range of $$[0, i - 1)$$. There are a similar conclusion if $$num[i + 1] > nums[i]$$ and in the
right side.

```java
public class FindPeakElement {
    
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        
        while(true) {
            mid = (l + r) / 2;
            if (mid - 1 >= 0 && nums[mid - 1] > nums[mid]) {
                r = mid - 1;
            } else if (mid + 1 < nums.length && nums[mid + 1] > nums[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
    }
}
```
