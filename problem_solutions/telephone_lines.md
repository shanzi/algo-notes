# Telephone Lines (POJ 3662)

This questions combines graph theory and binary search methods. Given a connected graph,
from a path from start node to end node you can drop `K` edges' costs. It is obviously that
we should drop those with highest cost. The key to this problem is that, for edges left,
the total cost is measured by one of the highest cost instead of the total cost.

So that we can using binary search to guess the highest possible single edge cost left and
verify if we can drop exactly K edges in the shortest path while all edges dropped has greater
cost than our guessed value. To achieve this, for a guessed cost `C`, we regard edges with cost `C' > C`
to have cost `1` and other edges' cost to be zero. Then if the shortest path in the new graph is less than
or equal to `K`, we know that it is possible to drop K edges in one of the path and let the highest cost
in left edge is not greater than `C`. Or, the guessed `C` is not valid, we should increase our guess.

We use dijkstra algorithm to fast find the sortest path cost in the transformed graph and binary search
to pick the lowest possible value `C`.

!CODEFILE "../poj/TelephoneLines.java"
