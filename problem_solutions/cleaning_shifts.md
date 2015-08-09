# Cleaning Shifts (POJ 3171)

This is a range covering problem with dynamic programming solutions. But as to improve the time efficiency,
we have to make use of segment tree to handle the minimum value of a range.

At first we sort the cows by their start range point. Then from the first cow and its available range 
`[l, r]`, we query the least possible cost with previous cows and the end points of their work period
ends in `[l - 1, r - 1]`, with the least possible cost in that range we can calculate the least cost
if we pick add cow to work. Then we keep the minimum value at time point `r`. Then the cost value at `E`
will be the final answer. We should return `-1` if that value is `Integer.MAX_VALUE` because that means
there is no way to cover the whole time span.

!CODEFILE "../poj/CleaningShifts.java"
