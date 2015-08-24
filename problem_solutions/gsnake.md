# gSnake

You are asked to simulate a snake game. The problem stands in how to represent the playground efficiently
as in the large case the width and height of it can be very large. Also we have to find a way to record
eaten fruit to avoid it to be eaten twice.

We can use a pair like structure to represent a cell on the playground. The key is to implement it's
`hashCode` and `equals` function correctly so that it can be put into and retrieve correctly by a `HashSet`.
We use a `LinkedList` to hold all positions the snake currently covers and use a set to hold position of eaten
fruit. To check if a move is valid, we also use a set to keep exactly same items with the `LinkedList` to enable
fast querying. To end the simulation without invalid steps in the middle, we need not move forward to $$10^9$$ seconds
as it will cost a lot. As after all turn direction action finished the snake won't change direction any more, we
only need to simulate as many as the number of width or height of seconds more as beyond that the snake's movement
will become a cycle.

!CODEFILE "../apac/2016/RoundA/GSnake.java"
