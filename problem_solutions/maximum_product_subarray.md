# Maximum Product Subarray

Find a subarray from an array contains positive, negative and zero values. There are a few things to be noticed:

1. Zeros divide the array into several segments and every time we meet a zero, we need to reset state.
2. If no zero appears, the product's absolution value of all elements before won't decrease.
3. Based on above, if the product from the begining of a segement to current position is positive, then
it is the max partial product at this position.
4. If the product is negative, the largest partial product possible is current product divided by
the greatest negative product has appeared(which must be the first negative product).
If no negative product has appeared, let the product it self compared to current max product.
5. To avoid integer exceed, we need to use `long` to save partial result. An example case is:
`[2, -1, Integer.MAX_VALUE]`, if we use `int` to save partial result, it may exceed range.

```java
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        
        long min = 1;
        long max = Integer.MIN_VALUE;
        long product = 1;
        
        for (int i : nums) {
            if (i == 0) {
                min = 1;
                product = 1;
                max = Math.max(max, 0);
            } else {
                product *= i;
                max = Math.max(max, Math.max(product, product / min));
                if (product < 0 && min == 1) {
                    min = product;
                }
            }
        }
        
        return (int)max;
    }
}
```
