# Permutations

Related problems: [Permuations](https://leetcode.com/problems/permutations/),
[Next Permuation](https://leetcode.com/problems/next-permutation/) and
[Permuation Sequence](https://leetcode.com/problems/permutation-sequence/). 

To generate next permutation of an array, we at first find the first `i` from right to left
that let `nums[i - 1] < [i]`. If `i = 0`, we have the elements in the array is always descending.
Otherwise, we find the first element that is greater than `nums[i - 1]` from left to right
and swap the to elements. Then we sort the array from `i` to end.

```java
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        
        int i, j, k, t;
        
        while (true) {
            ArrayList<Integer> first = new ArrayList<Integer>();
            for (int n : nums) first.add(n);
            result.add(first);
            
            for (i = nums.length - 1; i > 0; i--) {
                if (nums[i - 1] < nums[i]) break;
            }
            
            if (i <= 0) break;
            
            for (k = nums.length - 1; k >= i; k--) {
                if (nums[k] > nums[i - 1]) break;
            }
            
            t = nums[i - 1];
            nums[i - 1] = nums[k];
            nums[k] = t;
            
            Arrays.sort(nums, i, nums.length);
        }
        
        return result;
    }
}
```

To get the `k`th permutation directly, we at first find the factorials $$k!$$ of 0 to 9.
Then, from left to right, we skip as many as elements by counting use the factorials and find a
proper element to be placed there and move on to the next. We need a boolean array to mark if
a element has been used.


```java
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        int[] factor = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        
        boolean[] used = new boolean[n + 1];
        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= n; j++) {
                if (used[j]) continue;
                
                if (count + factor[i] < k) {
                    count += factor[i];
                } else {
                    used[j] = true;
                    res.append(j);
                    break;
                }
            }
        }
        
        return res.toString();
    }
}
```
