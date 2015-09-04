# Wooden Sticks (POJ 1065)

This problem is equivalent to asking you what is the minimum number of non-overlapping non-decreasing
subsequence can be found in the sequence. A straight forward solution may be that each time we find the
longest non-decreasing subsequence from the original sequence and remove the items. We do this repeatedly
until no elements left. This solution is correct but not time efficient enough.

Here we have to apply a proposition that the number of non-decreasing sub sequence is at least
the number of items of the longest decreasing subsequence in the original sequence. Let $$x$$
denote the number of items in the longest decreasing subsequence and $$l$$ denote the minimum
number of non-decreasing subsequence. Assume $$l < x$$ then we first remove the items in the decreasing
subsequence from the original sequence and then obviously we partition the remaining items into
$$l$$ non-decreasing subsequence. Then we put items in our decreasing subsequence back to the $$l$$ groups
of items. Obviously there must be at least two items be put into one group as $$l < x$$. But we note that
the relative order of items in subsequence must be the same as that in the original sequence,
so we have at least one group we contains two or more elements that is decreasing, thus that group can not
be a non-decreasing subsequence. Here we find a contradiction, so we must hvae $$l\ge x$$.

So the answer to this problem is just the number of items in the longest decreasing subsequence of the
origin sequence. We have to sort the sticks by their lengths first as a stick as two properties.

!CODEFILE "../poj/WoodenSticks.java"
