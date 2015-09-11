# Taking Metro

This problem gives a metro graph in a way that we know the time cost between stations in one metro line
as well as tunnels between metro lines and the time cost to go through it. We are also given a number
of how many minutes you'll have to wait to take on a metro line.

The general solution is to build the metro lines into a directed graph and use Dijkstra algorithm to solve it.
The main problem stands in how to handle the time of waiting a metro train to take on. We have two points that
we need to count this time cost in:

1. The first time you take on a metro line
2. When you switch from one line to another

Note that we should not count this time when we reached our destination. To handle this, we regard each station
as two virtual nodes in the graph. One of the nodes of a station is the virtual point a passenger get in and out
the station, the other is the virtual point that a passenger take on and off the train. So there is an edge
from the first virtual nodes with a time cost of that line but in reverse the edge has zero cost. We also regard
transfer tunnels as edges between the first type of nodes.

At last, when a query come, we always query on the first time of nodes, the dijkstra algorithm will direactly
return the total minimum time cost and `-1` if there is not a path between the two stations.

!CODEFILE "../apac/2015/RoundC/TakingMetro.java"
