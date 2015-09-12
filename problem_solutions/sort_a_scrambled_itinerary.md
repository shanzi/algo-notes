# Sort a Scrambled Itinerary

This turns out to be a quite simple problem. If we regard the tickets as edges in a graph
we will find that for each node there is only one edge in and on edge out. The whole graph
only has one path. The only task we need to do is to find the start node which do not have
any edge in. And start from this node we can traverse through the path and give the order of tickets.

!CODEFILE "../apac/2015/RoundD/SortAScrambledItinerary.java"
