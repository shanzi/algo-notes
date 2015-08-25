# River Hopscotch (POJ 3258)

An integrated application of greedy strategy and binary search. The question asks you to remove
`M` rocks in the river and let the minimum distance between every two adjoint rocks as large as possible.
Once we fixed a distance, we can use greedy strategy to verify if we can remove less than or equal to `M`
rocks to let minimum distance between rocks that is greater or equal to that value in $$O(N)$$ time,
then we can use an superior binary search to decide the best value for us.

To greedily verify if the value is feasible, we start from the first stone and once we found the next stone
is in a distance less than our value, we remove it. If at last we removed less than or equal to `M` stones,
the value is valid. Then we can increase the guess value in binary search.

!CODEFILE "../poj/RiverHopscotch.java"
