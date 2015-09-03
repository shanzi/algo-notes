# Stripies (POJ 1862)

The formular of this problem is hard to find. From the test case given, we may guess that we
should let the heaviest two stripies colide and then the third heaviest. The guess is correct
but it is not to be proven. The following formular gives a preliminary explain of this proposition.

Assume we have three stripies $$a$$, $$b$$ and $$c$$ and their weights are $$w_a$$, $$w_b$$ and $$w_c$$,
after the three stripies colide in an order of $$((a + b) + c)$$, the final weight will be
$$2\sqrt{2\sqrt{w_a\cdot w_b}\cdot w_c}$$, after square, it equals

$$
8\sqrt{w_a\cdot w_b}\cdot w_c = 8\sqrt{w_a\cdot w_b\cdot w_c^2}
$$

We see that $$c$$ contribute most to the final weight, if $$w_c$$ is low, the total weight will tend to be low.
So following this idea we should let the least heavy stripies colide with others as late as possible.

!CODEFILE "../poj/Stripies.java"
