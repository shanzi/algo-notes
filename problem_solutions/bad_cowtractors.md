# Bad Cowtractors (POJ 2377)

Here we are going to find the highest cost tree on the graph. To achieve this, we can just
reverse each edge's cost to its inverse value so that in the new graph, the minimum spanning tree
will be the tree cost the most. Then we just apply Prim algorithm and at last we inverse the sign of
total tree cost as answer.

!CODEFILE "../poj/CowAcrobats.java"
