# Balanced Lineup (POJ 3264)

It is a simple problem that need apply segment tree. We use two segment trees to maintain
maximum and minimum values on the array respectively. Then when a query comes we return
the difference of the maximum and minimum value we queried from these two segment trees.

!CODEFILE "../poj/BalancedLineUp.java"
