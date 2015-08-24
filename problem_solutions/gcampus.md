# gCampus

Given a bidirectional graph, you are asked to find those edges that is not on the shortest path of
every pairs of nodes. The key problem is how to find all edges on the shortest path and there maybe
two shortest path between a pair of node so we must find both.

The solution to this problem is for each node, we pick it as the start point and perform dijkstra algorithm
to find the shortest path from this point to all other points. Then we perform a depth first search on the
graph to find all edges on the shortest path. Let `dist[i]` denotes length of shortest path from start point `s`
to `i`, an edge `(u, v, cost)` is on one of shortest paths if and only if `dist[u] + cost = dist[v]`.

We run dijkstra algorithm `n`th instead of using Floyd algorithm as it may cost too much time on large case.

!CODEFILE "../apac/2016/RoundA/GCampus.java"
