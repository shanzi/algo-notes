# Allowance (POJ 3040)

This question asks you to find the ways to find a sum of coins that is larger or equal to (not exactly) a given value $$V$$.
As to save money and compute the maximum of times to achieve this. We apply greedy strategies. That is,
from the beginning, we always try to pay a lowest value with given coins that is larger or equal to $$V$$.
We repeatly do this until the coins remains is not available for a valid payment. 

To do this, we first pick coins from that of the largest value and move to coins of lower value and greedily
spend them. If at last we get a sum that is exactly $$V$$ we stop here. If at last we get a sum less than
$$V$$, we have to pick coins reversely from the smallest face value until we can cover the payment. By doing
this we can get the lowest sum we can get from coins left that is valid.

!CODEFILE "../poj/Allowance.java"
