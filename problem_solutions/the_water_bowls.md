# The Water Bowls (POJ 3185)

We add two virtual bowls before the head after the tail respectively. At first we let the first
bowl initially drinkable and the second time we let it initially undrinkable. Each time we let
the cow reverse the bowls from right to left. During each action, the left one of the three bowls
that is reversed will be the first undrinkable bowl and no bowls on the left is undrinkable.
From left to right we do this and we pick the least number of actions among the two time as our answer.

!CODEFILE "../poj/TheWaterBowls.java"
