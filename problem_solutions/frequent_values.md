# Frequent Values (POJ 3368)

The answer to this question applys both divide and conquer method and segment trees. When we are querying
the frequent value in a given range, we can divide the range into two halves and query in them repectively.
Then the most frequent values in the whole range may among:

1. Most frequent value in the left range.
2. Most frequent value in the right range.
3. A frequent value which goes from left to right range.

As the values in the array is sorted at first, so we can get the most frequent value over the boundary
between two halves by checking if the last item from the left is the same as the first time from the right.
So we can handle three segment trees. The first keeps the number of most frequent value in a range,
the second holds the number of first item in the range and the last holds the number of last item in the range.

Each time we update and query the array, we make uses of the three trees to give a final answer.

!CODEFILE "../poj/FrequentValues.java"
