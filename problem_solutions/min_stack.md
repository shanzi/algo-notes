# Min stack

Build a stack that can get the minimal element in the stack in constant time.
A obviously solution is using another stack to save corresponding min value
at each stack size. There are two optimizing ways, one is only push value that is
less or equal to current min value to `minStack`, the other is counting the times
one min value occurs and pop the value only when count become zero.

The following code shows the implementation of the first optimizing way:

```java
class MinStack {
    Stack<Integer> innerStack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    
    public void push(int x) {
        innerStack.push(x);
        
        if (minStack.isEmpty() || x < minStack.peek().intValue()) minStack.push(x);
        else minStack.push(minStack.peek());
    }

    public void pop() {
        minStack.pop();
        innerStack.pop();
    }

    public int top() {
        return innerStack.peek().intValue();
    }

    public int getMin() {
        return minStack.peek().intValue();
    }
}
```

