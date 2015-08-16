# Bad Hair Day

The strategy to solve this problem is simple. At first we put all cows in line, then from
right to left, we check each cow. At the same time we maintain a increasing stack that
all items from top to bottom is increasing. Then we a cow comes, we first pop all records in the
stack that is shorter than it, then we get from that cow's right to the top element of stack,
all cows in this range is shorter than current one. We count the number and add current cow onto the stack at last.

!CODEFILE "../poj/BadHairDay.java"
