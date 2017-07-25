# Container with Most Water

A brute force solution is to try each pair of two lines and find the maximum container capacity.
It is not acceptable in time cost. A smarter way is to use two ends narrowing method to get
a linear time complexity.

Let's say at first we pick two lines at two ends, at this time the container as the greatest width.
If we are narrowing the width if container, we expect to improve height of lines at container's sides.
So which end to move first? Let's say the two ends are `height[i]` and `height[j]`,
if `height[i] > height[j]` and we move `i` to the right, `min(height[i], height[j])` won't increase,
so there is no point moving `i`. Conversely, if `height[i] < height[j]`, there is also no point
moving `j` to left. So we always move the end of shorter height. If two sides are of the same height,
we can move both.

Why is this algorithm correct? Let's way the container with most capacity is with lines at `a` and `b`.
If there is a line `a'` is higher than `a` and in the left side of `a`, we can replace `a` with `a'`
and get a bigger container. It is the same for `b`. So there must be:

1. For any `i < a`, `height[i] < height[a]`
1. For any `j > b`, `height[j] < height[b]`

By repeatedly moving two ends, at some time we must have `i` or `j` at the position `a` or `b` first.
Let's say `i` meet `a` first. We know that at this time `height[j]` must be less than `height[b]`, but we can also
get that `height[j] < height[a]`. If `height[i] >= height[b]`, the container from `a` to `j` has the same
minimum height of sides but is wider, so it will be able to contains more water, that is a contradiction.
The same for `j` meet `b` first.

That is, at some moment, we will have `i = a` and `j = b`, thus we get the maximum capacity in record.

```java
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = 0;
        
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            
            if (height[l] > height[r]) r--;
            else l++;
        }
        
        return max;
    }
}
```
