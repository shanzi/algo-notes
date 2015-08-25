# Out of Hay (POJ 2395)

This is mutation of minimum spanning tree problem. Consider Prim algorithm,
every time we pick edge of the lowest cost to added on the minimum spanning tree if it won't form
a cycle. As visit a farm twice makes now sense, so the minimum spanning tree will be Bessie's routes
and also, the most costly path in the minimum spanning tree is the answer. The key is, based on the 
spanning tree, if we add edges that cost less than the most costly edge we pick in the minimum spanning tree,
it won't affect the answer. The minimum spanning tree is the least way to be able to travel to each farms.

!CODEFILE "../poj/OutOfHay.java"
