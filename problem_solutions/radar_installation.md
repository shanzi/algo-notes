# Radar Installation (POJ 1328)

A problem combines greedy strategy and computing graphics techniques.
At first we sort the position of islands according to `x` first and `y` last.
Then from left to right, we find the range on `X` axis a radar should be at
if we'd like it to cover this island. Then we comes the next island, we can narrow the range
by intersect two ranges from two islands. Keep doing this and when the next island's range
has new intersection with current range, we have to add a new radar. Repeat this we will get
the minimum number of radar that is needed.

!CODEFILE "../poj/RadarInstallation.java"
