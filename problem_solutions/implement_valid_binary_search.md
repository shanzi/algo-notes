# Implement Valid Binary Search

Binary search is a fundamental algorithm. But it is very hard to implement a 100 percent correct one.
When implementing this algorithm, there are a few things should be take care of:

1. Your code should handle array with zero and one elements.
2. To calculate the mid point, `left + ((right - left) >> 1)` is better than `(left + right) / 2`
as it won't exceed range of `int` value.
3. Take care of boundary changes to avoid infinite looping.

Below are an example of implementation:

```java
public class BinarySearch {
    public int search(int[] array, int target) {
        if (array.length == 0) return -1;

        int l = 0;
        int r = array.length - 1;
        int mid;

        while (l <= r) { // we are using l <= r here so that array with only one elements will be handled
            mid = l + ((r - l) >> 1); // get mid with out (l + r) so that it won't exceed range
            if (array[mid] == target) return mid;
            else if (array[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1; // minus one here to avoid infinite looping.
            }
        }

        return -1;
    }
}
```
