# Moo University

Given several cows, their scores and financial aid requirements. You are asked
to find the subset from them of count `N` and let the medium score of those cows as high as
possible while the total financial aid fee is no greater than a given value `F`.

This problem applys `PriorityQueue`. To find the highest medium that conforms to those conditions,
we need not realy find out the optimal subset. Instead, we fixed the medium value and try to find
if we can find valid subset on the left and right of that medium value. As `N` will be an odd value,
so the medium value must be one of the scores.  There must be exactly `K = N / 2` items in the subset
that is lower and greater than that medium value respectly. So if we sort all the scores, the medium
can only from range `[K, N - k]`. And on the left and right of the medium value we try to find `K` items
that with lowest financial cost in the sorted array. If the three parts has a lower or equal total cost
than the given `F`, that medium value is valid.

To solve this problem efficiently, at first from left to right we use a Priority Queue to maintain `K` elements
that with lowest financial aid requirments and sum to total cost. At each step we store the cost at the
corresponding position. That is we have an array that each element `i` holds the minimum `K` cows' financial cost
at the left of index `K + i`. Then we process the array the second time and this time we maintains `K` minimum
financial cost on the right. Then along with the medium value at center, we can find the minimum financial cost
possible with one score as medium value and check if it is valid. We check medium values from right to left
so that the first time we meet a valid one it will be greatest possible. If all scores is not valid, we return `-1`.

!CODEFILE "../poj/MooUniversity.java"
