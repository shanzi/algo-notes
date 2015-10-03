# Travel

Given graph of several roads. As for each road, the time cost to pass it depends on
the start time you get on the road. The roads are bidirectinal and time cost to pass it
is the same if you start at the same time. The complexity of the problem is reduced to that
the time to get through a road is integer hours and also you start time is exactly the
beginning of one of 24 hours in a day. Now given the start point, target point and set off time,
you are asked to give the least possible time of traveling.

This is a mutaion of shortest path problem. As the starting time and time costs are all integer numbers,
we spawn the node represents a city into 24 different nodes which each stands for a starting time at
this city. Then we connect these nodes to corresponding nodes of the next city. Note the roads are bidirectional,
but we should not directly connect a reversed edge from the target node to current node. We can not
travel back in time, so if we start from the target node and go to current city, we are goint to arrived at
different time so that the back edge should be connected a different node of current city.

As for large case, we have already known the start point for each case and we can only have 24 different start time.
Use dijkstra algorithm we can calculate the sortest path from a source node to all other nodes. Thus, we directly
calculate the results for every start time and look up for cost for each destination query. This is faster than
calculate the shortest path each query as the query number might be far large than 24.

!CODEFILE "../apac/2016/RoundB/Travel.java"
