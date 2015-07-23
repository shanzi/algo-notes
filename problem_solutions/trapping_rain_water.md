# Trapping Rain Water

This is a simple problem. At each index `i`, let `left[i]` be the higest bar on its left
and `right[i]` be the highest bar on its right, then the depth of water at this position
equals to `max(left[i], right[i]) - height[i]`. Note the depth of water must not be negative,
so if `height[i]` is greater than the highest possible top edge of water(`max(left[i], right[i]`),
we count it as zero.

```java
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height.length == 0) return 0;
        
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        
        for (int i = 1; i < left.length; i++) {
            left[i] = Math.max(height[i - 1], left[i - 1]);
        }
        
        for (int i = right.length - 2; i >= 0; i--) {
            right[i] = Math.max(height[i + 1], right[i + 1]);
        }
        
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            sum += Math.max(0, Math.min(left[i], right[i]) - height[i]);
        }
        
        return sum;
    }
}
```
