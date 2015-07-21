# Largest Rectangle in Histogram

This is a useful problem, the same techniques can also be used to calculate
[Maximal Rectangle/Square](problem_solutions/maximal_rectangle_square.md).

The idea is to use a stack and let the stack keep indexes of a series of increasing
height in the array. Let's we get position `i` now, if `height[i]` is greater than
`height[stack.peek()]` we just push it into the stack. Or, we pop one element from the stack if
there is any and get the height `h`. At this point, we know, every heights from the one after new peek of stack to
the item before `i` are all greater or equal to `h`. So we can get a rectangle with `h` as height and
`i - stack.peek() - 1` as width. If stack is empty, we let the peek to be `-1`.

```java
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int h = height[stack.pop()];
                int l = (stack.isEmpty() ? -1 : stack.peek());
                max = Math.max(max, h * (i - l - 1));
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int h = height[stack.pop()];
            int l = (stack.isEmpty() ? -1 : stack.peek());
            max = Math.max(max, h * (height.length - l - 1));
        }
        
        return max;
    }
}
```

As every index will be push into and pop out of the stack once, this algorithm cost $$O(N)$$ time,
and in the worst case it may cost $$O(N)$$ space.
