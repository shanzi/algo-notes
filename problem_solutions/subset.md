# Subset (POJ 3977)

At first we iterate all subsets of the first half of the array and calculate all the sums and the minimum number
of elements to make the sum. Then we put the sums in an array and sort them. Then we iterate the all subsets
last half of array and count the number of items. For each `sum` in from the last half, we build use binary search
method to find the neareast value to `-sum` in the cached array of sums from first half. The two value
will make the a least absolute value sum. We use a variable to keep tracking the minimum absolute sum value
and minimum number of items to make it and finally return the answer.

!CODEFILE "../poj/Subset.java"

