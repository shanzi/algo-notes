# GBus Count

Given several cities numbering from `1` to `N`. There are several buses and each of them covers
a consecutive range of cities. Given all these buses and a series of queries, you are asked to return
how many buses have covered a city.

This problem is extremely proper for Binary Indexed Tree. Let's say a bus covers cities from `a` to `b`
inclusively, then we add 1 at position `a` and minus 1 at position `b + 1` in the BIT. Then the query
result of position `i` is how many cities that covers the `i`th city.

!CODEFILE "../apac/2015/RoundD/GBusCount.java"
