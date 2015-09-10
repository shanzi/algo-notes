# Parentheses Order

This turns out to be a counting problem. We convert the problem to get path from an $$N\times N$$ grid's
top left point to its bottom right but without go below to points below the diagram. 
Then we start from the graph of two straight line and going down greedily concerns about the $$k$$ value
until $$k = 0$$. Then we restore the parentheses from the graph.

!CODEFILE "../apac/2015/RoundB/ParenthesesOrder.java"
