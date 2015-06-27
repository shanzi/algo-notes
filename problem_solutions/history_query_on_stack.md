# History Query on Stack

Define your own stack structure to support the following three operations:

1. `push` push a new element into the stack
2. `pop` pop a new element into the stack
3. `query` query the state of the stack after operation `i`

Assume `i = 0` is the initial state of the stack, when the stack is empty. All
other operations including `query` itself is numbered as 1, 2, 3 and etc.
Design a data structure to support these actions and let's the time and space
as efficient as possible.

We can save the operation history and the operating number into a history list,
once a query comes, we redo every thing from the beginning and get the status
of stack. This solution cost a lot in query time and is not acceptable.

If you are using a array to store the element of stack. There are not many things
we can do to improve the space and query cost. But if we open our mind and use
a linked list to store elements, we can come up a quite good solution.

Using a singly linked list, we use the head as the top of stack. After each operation,
we save a pointer to the head in history. Then, when queried the `i`th operation,
we just return the head saved in the history list at position `i`. In fact,
reference to every element pushed in the stack will be kept and the singly linked list
along with the elements saved in the history list is constructed as a directed acyclic graph.

Following code shows how to do it:

!CODEFILE "../common/HistoryStack.java"

Note that we are using an `ArrayList` to save the history so that get the `i`th history
has constant time complexity. A normal linked list need an $$O(N)$$'s time cost to
access the `i`th element which is no better than redo all the history. Also we can use
a linked list that each node at index `i` has a pointer to the node `i * 2` so that
we can access a node in $$O(\log N)$$. Added items in the history list won't be remove or changed,
so it is extremely suitable to similar methods that could improve accessing speed but may cost
a lot to remove or modify items in the list.
