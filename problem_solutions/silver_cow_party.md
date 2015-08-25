# Silver Cow Party (POJ 3268)

This is also a problem that demands you caculate distance between every pair of vertices.
Instead of simply apply Floyd algorithm, we have to run Dijkstra algorithm N times.
That is because the time complexity of Floyd algorithm will be $$O(N^3)$$ where N is the number of
vertices, but if using a priority queue, a single round of Dijkstra algorithm cost $$O(N\log E)$$
so the total time cost is proportional to $$N^2 \log N$$ which is a little faster than Floyd and
can pass the tests without time out.

!CODEFILE "../poj/SilverCowParty.java"
