# Milking Time (POJ 3616)

This problem is a variant of interval cover or set selection problem. You have to pick a collection
of intervals from the given set and at the same time satisfied that:

1. No two intervals is overlapping
2. The benifit from selected intervals is as large as possible.

A general solution to this kind of solution is at first we sort the intervals by their start time,
then from the first interval, we add current interval's benefit with the largest benefit that end time
before current start time and then update the max benefit of current end time.

Let $$DP[i]$$ denotes the max benefit possible from a non-overlapping subset of intervals whose
end time are before or equal to $$i$$, then we have:

$$
DP[e_i] = \max\left\{DP[e_i], DP[s_i] + v_i\right\}
$$

Where $$e_i$$, $$s_i$$ and $$v_i$$ are respectively end time, start time and benefit of the $$i$$th interval.

!CODEFILE "../poj/MilkingTime.java"
