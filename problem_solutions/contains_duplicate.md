# Contains Duplicate

*Contains Duplicate* is a series problems on LeetCode. It contains three different levels
and the third level is a very important one.

## Level one: check if an array contains duplicate items

It doesn't matter what the index of two same item are. So there are two type of solutions.

1. We sort the array and check two adjoin items one by one to check if there are a pair
are the same. As after sorting, two same item must be arranged at an adjoin position,
so we can always find them.
2. Use a `HashSet` to keep all elements before, when we get to a new item, check if
it has already been in the set. If true, the array contains duplicate items.

## Level two: duplicate item is required to no far than k position

The same with level one except that distance of two duplicate items must be lower or equal to `k`.
Make use of the former `HashSet` solution, everytime we remove item in time to keep the set
always contains `k` items before current item to check.

Sample code:
```java
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> dedupSet = new HashSet<Integer>(k + 1);
        for (int i = 0; i < nums.length; i++) {
            
            if (dedupSet.contains(nums[i])) return true;
            dedupSet.add(nums[i]);
            if (i - k >= 0) dedupSet.remove(nums[i - k]);
        }
        return false;
    }
}
```

## Level three: items in a range

Please refer to the problem statement at [LeetCode](https://leetcode.com/problems/contains-duplicate-iii/).
Now we define two items are duplicated if the absolution difference between two items is below or equal to `t`,
and the difference of item indexex should be at most `k`.

A straightforward solution is for the item at position `i` we check items from `i - k` to `i - 1` to find
if there is an item conforms to the condition. This solution's time cost is $$O(N\times k)$$ which is not enough.
Is there a way that we can find nearest number in a set to a given value? Yes, of course. There are serveral
ways to do this, one of the most convenient is using a binary search tree.

Java has provide a `TreeSet` structure which provide everything we need. There are four useful functions
to use:

1. `E floor(E e)`: return the greatest element less than or equal to `e`
2. `E ceiling(E e)`: return the least element greater than or equal to `e`
3. `E lower(E e): return the greatest element strictly less than `e`
4. `E higher(E e): return the least element strictly greater than `e`.

There are several important points that should be noted. At first, `floor` and `lower`
returns a number less than (or equal to) `e` while `ceiling` and `higher` return number
greater than (to equal to) `e`. The second, if there is no element can be found for given number,
the four functions will return `null`. `null` is a type that you can not assign it to `int` type.
You can neither compare `null` with an integer. Your code must be able to handle this.

For more detailed introduction of `TreeSet`, please refer to
[Java Document](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html).

As for this problem, there still is a point: the integer may exceed its range when we are
calculating the difference between two number. For example, when we are trying to get
the difference of `a` and `b` by `Math.abs(a - b)`, what if `a >= 0` and `b = Integer.MIN_VALUE`?
We have to transform the two number into `long` before we minus them.

Accepted sample code:
!CODEFILE "../leetcode/ContainsDuplicateIII.java"

Using a Binary Search Tree or `TreeSet`, get a element less or greater than given number has
a time complexity of $$O(\log k)$$. So the total time cost will be $$O(N\log k)$$.
