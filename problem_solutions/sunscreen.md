# Sunscreen

Given a series of cows with min an max sun shine acceptation and some bottles of lotion,
each lotion has an effective SPF value and count of cows that can use it. A bottle of lotion
is required to be in acceptation range of a cow if it is applied to that cow. The question is
asking the maximum number of cows that can use lotion.

The solution combines greedy strategy as well as application of `PriorityQueue`.
At first we sort the cows according to there min value and lotion according to their SPF value.
Then from the first lotion, we first find all cows that min value is below the SPF value,
We add them to the `PriorityQueue` so that each time we can get a cow with lowest max value out.
It is obviously if we pick out cows in this order, if a cow's max is lower than current SPF, it
and all cows before won't be able to apply this lotion. We also have, if a bottle of lotion can be
applied to cow `a` and for a bottle of lotion that has higher SPF but lower than `a.max` value,
`a` can also apply the latter bottle of lotion. But reversely this condition won't holds.
That's why the greedy strategy will work. Every time we apply lotion to cows in the range but with
lowest max value, that won't affect the total count.

!CODEFILE "../poj/Sunscreen.java"
