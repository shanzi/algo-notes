# Majority Element

Majority Element is a classical problem. There are two algorithm is know can solve this problem
in a time cost of $$O(N)$$.

The first is that we find the median number in the array, it must be the majority element.
The simplest way to get a median number is to sort the array, but its time cost is $$O(N\log N)$$.
There is a way to find the median number in $$O(N)$$ based on array partition.

```java
public class Solution {
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private int partition(int[] nums, int l, int r) {
        int pivot = nums[l];
        int i = r + 1;
        int m = r + 1;
        do {
            while (nums[--i] < pivot);
            swap(nums, --m, i);
        } while (i > l);
        return m;
    }
    
    private int median(int[] nums, int l, int r, int k) {
        int p = partition(nums, l, r);
        if (p == k) return nums[k];
        else if (p < k) return median(nums, p + 1, r, k);
        else return median(nums, l, p - 1, k);
    }
    
    public int majorityElement(int[] nums) {
        return median(nums, 0, nums.length - 1, nums.length / 2);
    }
}
```

Although its time complexity is $$O(N)$$, but it uses a space of $$O(\log N)$$.
It is also not fast in enough on LeetCode.

There are also several other solutions such as Using a `HashTable` to count element numbers,
divide and conquer and so on. But the best solusion is commonly thought to be Moore Voting
algorithm. Moore voting algorithm's time cost is $$O(N)$$ and space cost is $$O(1)$$.

```java
public class Solution {
    public int majorityElement(int[] nums) {
        int majority = nums[0];
        int sameCount = 1;
        
        for (int i = 1; i < nums.length; i++) {
            
            if (nums[i] != majority) {
                sameCount--;
                if (sameCount == 0) {
                    majority = nums[i];
                    sameCount++;
                }
            } else {
                sameCount++;
            }
            
            
        }
        return majority;
    }
}
```

More description on Moore voting algorithm and other algorithms please refer to
the [solution page](https://leetcode.com/problems/majority-element/solution/) on LeetCode.
