# N-Sum

N-Sum is a common problem in interview, there are four different related problems on LeetCode,
they are [Two Sum](https://leetcode.com/problems/two-sum/), [3Sum](https://leetcode.com/problems/3sum/),
[3Sum Closest](https://leetcode.com/problems/3sum-closest/) and [4Sum](https://leetcode.com/problems/4sum/).

Generally speaking, all N-Sum problem with `N > 2` is based on Two Sum. We will talk start from it.

## Two sum

To solve two sum, we first need to sort the array to make sure elements in it is in an ascending order,
so the time complexity of two sum won't be lower than $$O(N\log N)$$ in average if the array given
is not sorted. After sort the array, we have two strategies to further solve this question.

The first one is we iterate all possible first element and use binary search to find the second. The total
time complexity of algorithm analysis is still $$O(N\log N)$$ but it will be slower than the second one. 
Neither to say if the array given is sorted, this solution is not optimal any more.

The second one is that we uses two index pointer `l` and `r` and narrowing the distance between `l` and `r`
step by step. As the array is sorted, if `nums[l] + nums[r] < target`, then for any `i` less than `l`,
we have `nums[i] + nums[r] < target` too. Conversely, if `nums[l] + nums[r] > target`, for any `j` that is 
greater than `r`, we also `nums[l] + nums[r] > target` too. So if it is the former case, we increase `l` by one,
and for the latter case we decrease `r` by one.

This algorithm is corrent because after some steps, there must be one of the cases below occurs:

1. There are no two numbers sum up to `target`. Then `l` meets `r` at last.
2. There are two numbers sum up to `target`, let indexes of two number are `a` and `b`, then:
    1. `l` may meet `a` first, in this case, `nums[l] + nums[r]` must be greater than `target` until
        `r` decreases to `b`.
    2. `r` may meet `b` first, in this case, `nums[l] + nums[r]` must be less than `target` until
        `l` increases to `a`.

Thus we can always find `a` and `b` if there exists such two numbers in the array.

In the Two Sum problem on LeetCode, indexes of `a` and `b` is asked to be returned instead of two values themselves.
Hence we have to keep a mapping from a values after sort to its index before sort. Below shows a way to do this:

```java
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Integer[] indexes = new Integer[nums.length];
        for (int i = 0; i < indexes.length; i++) indexes[i] = i;
        
        Arrays.sort(indexes, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return nums[a] - nums[b];
            }
        });
        
        int l = 0;
        int r = indexes.length - 1;
        int sum;
        while (l < r) {
            sum = nums[indexes[l]] + nums[indexes[r]];
            if ( sum < target) l++;
            else if (sum > target) r--;
            else {
                int[] res = {Math.min(indexes[l], indexes[r]) + 1, Math.max(indexes[l], indexes[r]) + 1};
                return res;
            }
        }
        
        int[] res = {-1, -1};
        return res;
    }
}
```

## 3Sum and 3Sum closest

From $$N \ge 3$$, solutions to  N-Sum problem become superior programs call two sum as sub-program.
For 3Sum, we first iterate each element in the array as the first and perform two sum on the range
after that element on the array. When applying two sum, we take `target - nums[i]` as the target
while `nums[i]` is the first element we pick.

In the 3Sum problem on LeetCode, note that all valid pairs is asked to be returned, so we can not
just stop after we find first three elements that sum to `target`. What's more, as to avoid duplicated results,
we have to skip all same `l` or `r` ahead to get new `l` and `r` as well as skip the same first element we picked.

```java
public class Solutoin3Sum{
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        
        int t, l, r;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            
            t = -nums[i];
            l = i + 1;
            r = nums.length - 1;
            
            while (l < r) {
                if (nums[l] + nums[r] < t) l++;
                else if (nums[l] + nums[r] > t) r--;
                else {
                    ArrayList<Integer> newcomb = new ArrayList<Integer>(3);
                    newcomb.add(nums[i]);
                    newcomb.add(nums[l]);
                    newcomb.add(nums[r]);
                    result.add(newcomb);
                    
                    while (l + 1 < r && nums[l + 1] == nums[l]) l++;
                    while (r - 1 > l && nums[r - 1] == nums[r]) r--;
                    l++;
                    r--;
                }
            }
            
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
        }
        
        return result;
    }
}
```

3Sum Closest is similar to 3Sum, but the sum with minimum difference with `target` is asked to be returned.
Becareful, it is not the minimum difference itself is asked.

```java
public class Solution3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2] - target;
        int l, r, sum;
        
        for (int i = 0; i < nums.length - 2; i++) {
            l = i + 1;
            r = nums.length - 1;
            
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(closest - target) > Math.abs(sum - target)) closest = sum;
                if (sum < target) l++;
                else if (sum > target) r--;
                else break;
            }
        }
        
        return closest;
    }
}
```

## 4Sum

4Sum need the same strategy of 3Sum to solve, we iterate a tuple of two first elements and use two sum to find
the other two remained. The time complexity is $$O(N^3)$$ even if we take sorting cost into consider.
The same as 3Sum, we need take care of multiple results and remove duplicates.

```java
public class Solution4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        
        int l, r, t, sum;
        
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                t = target - nums[i] - nums[j];
                
                l = j + 1;
                r = nums.length - 1;
                
                while (l < r) {
                    sum = nums[l] + nums[r];
                    if (sum < t) l++;
                    else if (sum > t) r--;
                    else {
                        ArrayList<Integer> newres = new ArrayList<Integer>();
                        
                        newres.add(nums[i]);
                        newres.add(nums[j]);
                        newres.add(nums[l]);
                        newres.add(nums[r]);
                        
                        result.add(newres);
                        
                        do {
                            l++;
                        } while (l < r && nums[l] == nums[l - 1]);
                        
                        do {
                            r--;
                        } while (l < r && nums[r] == nums[r + 1]);
                    }
                }
                
                while (j + 1 < nums.length && nums[j + 1] == nums[j]) j++;
            }
            
            while (i + 1 < nums.length && nums[i + 1] == nums[i]) i++;
        }
        
        return result;
    }
}
```
