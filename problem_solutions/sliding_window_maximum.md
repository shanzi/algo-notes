# Sliding Window Maximum

There are two ways to solve this problem. The first one is using a `TreeMap`, the second one is to
a `deque`(`LinkedList`).

The `TreeMap` solution is straight forward. As the keys of a `TreeMap` is sorted so we always keep
elements in sliding window in the `TreeMap` with the value to be count of times a key appears.
When sliding the window, we add new key or increase existing key's count of current item and decrease
count or remove key of the number just moved out the sliding window. So the keys in the `TreeMap` are
aways elements in the sliding window. Then current maximum value is `TreeMap`'s last key.

To add, modify and remove keys in a `TreeMap` cost $$O(\log k)$$ time thus the total time complexity
of this solution will be $$O(n\log k)$$. It is linearithmic instead of linear time cost but is good enough
to pass the test on LeetCode.

```java
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return nums;
        
        int[] res = new int[nums.length - k + 1];
        TreeMap<Integer, Integer> maxqueue = new TreeMap<Integer, Integer>();
        
        for (int i = 0; i < k; i++) {
            if (maxqueue.containsKey(nums[i])) {
                maxqueue.put(nums[i], maxqueue.get(nums[i]) + 1);
            } else {
                maxqueue.put(nums[i], 1);
            }
        }
        
        res[0] = maxqueue.lastKey();
        
        for (int i = k; i < nums.length; i++) {
            res[i - k + 1] = Math.max(nums[i], res[i - k]);
            
            if (maxqueue.containsKey(nums[i])) {
                maxqueue.put(nums[i], maxqueue.get(nums[i]) + 1);
            } else {
                maxqueue.put(nums[i], 1);
            }
            
            int count = maxqueue.get(nums[i - k]);
            
            if (count > 1) {
                maxqueue.put(nums[i - k], count - 1);
            } else {
                maxqueue.remove(nums[i - k]);
                if (nums[i - k] == res[i - k + 1]) {
                    res[i - k + 1] = maxqueue.lastKey();
                }
            }
        }
        
        return res;
    }
}
```

The second way is better. It uses a `deque` to handle a queue from least value to greatest value.
Let's say we get a maximum element at index $$i$$, then all elements at indexes of $$j < i$$ won't
be picked as maximum element for future sliding window positions because when the element at $$i$$
is moved out of sliding window, then these elements must have already been out of window. Thus,
we need only maintain a queue contains the greatest element in the slinding window as well as
the elements after that element and less than that value and let the values in the queue is in descending order.

To achieve this, each time we meet a new element, we pop all elements that is less than it in the queue
and add it at last. When we move a element out of sliding window, we just check if it is among the greatest
elements, if true remove one or we should do nothing as that element won't corresponding to one element in the queue.

```java
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return nums;
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> maxqueue = new LinkedList<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                res[i - k] = maxqueue.getFirst();
                
                if (nums[i - k] == maxqueue.getFirst()) {
                    maxqueue.pollFirst();
                }
            }
            
            while (!maxqueue.isEmpty() && maxqueue.getLast() < nums[i]) maxqueue.pollLast();
            maxqueue.addLast(nums[i]);
        }
        
        res[res.length - 1] = maxqueue.getFirst();
        return res;
    }
}
```
