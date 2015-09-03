# Yogurt Factory (POJ 2393)

To apply greedy strategy, we decide production at one day when it is needed in the future
instead of deciding it in ahead of time. Let's say we are at week $$i$$ now and we need to deliver
$$Y_i$$ units of yogurt, then we always pick the most save week to produce this amount of yogurt in
week $$[0, i]$$. The cost of each week $$ j \in [0, i]$$ will be

$$
cost_j(i) = C_j\times Y_i + (i - j) \times S
$$

To find the lowest cost $$cost_j$$ by traversing all weeks before is not acceptable. It cost too much time.
But let't dig into the formular above more deeper, we will find that the lowest the $$j$$ in $$cost_j$$ won't
decrease when we are pushing forward. What's more. If we are at $$i$$ and $$cost_i \le cost_j$$ where $$j \le i$$,
then for any future weeks $$k$$ we always have

$$
cost_i(k) \le cost_j(k)
$$

So in the $$i$$th week we can verify if current $$cost_i$$ is more save and update the most save week
according to the result.

!CODEFILE "../poj/YogurtFactory.java"
