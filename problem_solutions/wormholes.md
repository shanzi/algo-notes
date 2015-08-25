# Wormholes (POJ 3259)

This is a typical problem that asks you to decide if there is a negative cycle in the graph.
In fact, if there is a negative cycle, you can always travel in the cycle serval times to go back to
a early enough point and then go back to the start point.

To check negative cycle, we use Bellman-Ford algorithm. Let the vertices count to be $$V$$,
we loop for $$V$$ times and each time we go over each edge to update shortest distance from start point
to other point. If these distances are updated in the last loop, then there must be a negative cycle in the graph.

!CODEFILE "../poj/Wormholes.java"
