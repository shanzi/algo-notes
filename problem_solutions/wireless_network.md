# Wireless Network

This is a typical application of UnionFind. To solve this problem, we maintain a `HashSet` contains
all fixed computers. When a new computer is fixed, we check distances from this computer to previously
fixed computer and if the distance is less than limit, we union them in the union-find set. When a 
check connectivity operation comes, we use the union find set to check if two computer is connected.

To solve this problem on POJ, we may not use `Scanner` as it may be too slow for inputing. A `BufferedReader`
is a better choice.

!CODEFILE "../poj/WirelessNetwork.java"
