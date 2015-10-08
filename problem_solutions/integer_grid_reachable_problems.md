# Integer Grid Reachable Problem

Given a one or two dimensional space. At first you are at the zero point. You can only go
to another integer grid point in the space and the step you can make is limited by some conditions.
Then, it is asked that if you can reach a target point in the space.

This kind of problems concerns about some common mathematics knowledge. At first let us start from
a simple one dimension instance.

## Move on a line

Let's say at first you are at zero point on the like. A set lengths of moves is given and you can only
move by a distance in that set. You can move in both directions. Now you are asked if you can reach
a target position $$t$$.

At first let's consider we can move by distances of $$x$$ and $$y$$. Assume $$x$$ and $$y$$ are coprimes,
then we have a proposition that we can always find $$a$$ and $$b$$ so that $$ax + by = 1$$. This is
according to [extended Euclidean algorithm](https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm).
Also we can easily find that if $$x$$ and $$y$$ are not coprime. That is $$gcd(x, y) = d > 1$$ where
$$gcd$$ means the greatest common divisor. So we have $$x = d\cdot x'$$ and $$y = d\cdot y'$$ where
$$x'$$ and $$y'$$ are coprime. Then we obviously can get $$a$$, $$b$$ subject to $$ax + by = d(ax' + by') = d$$
Both $$a$$ and $$b$$ can be get by extended Euclidean algorithm. But there are still a stronger identity
gives that for any $$x$$ and $$y$$ if we have $$ax + by = m$$ then we must have $$m$$ is a multiple of $$d$$.
The identity is named [Bézout's identity](https://en.wikipedia.org/wiki/Bézout%27s_identity).

With this identity, the problem is easy to solve as it is in fact equivalent to asked if there exists
integer solution $$a$$, $$b$$ for equation $$ax + by = m$$. More generally, if we have more possible steps
$$x_1, x_2, x_3\cdots x_n$$, the equation $$a_1\cdot x_1 + a_2\cdot x_2 + \cdots + a_n\cdot x_n = m$$
has integer solutions if and only if $$m$$ is divisible by $$gcd(x_1, x_2, \cdots, x_n)$$.

## Move on a plane

In two dimensional case, the problem is a little more complex. The move is usually limited by pairs of
move in two dimension: $$(x_1, y_1), (x_2, y_2), \cdots, (x_n, y_n)$$. Assume the set given conforms to the
following two properties:

1. If $$(x, y)$$ in the set, then $$(y, x)$$ must also in the set.
2. If $$(x, y)$$ in the set, then $$(-x, y)$$, $$(x, -y)$$ and $$(-x, -y)$$ must also in the set.

As for such an instance of problem, we have some simpler solution. We can consider the reachability
subject to two dimensions respectively. Let's pick $$X$$ dimension for instance.

At first, we can start from $$(0, 0)$$ to $$(2x_i, 0)$$ if we first go to $$(x_i, y_i)$$ then
we go by $$(x_i, -y_i)$$ and thus get to that point for any $$(x_i, y_i)$$. Then, let $$x_d$$ to be
the greatest common divisor of all $$x_i$$, then we can prove that we can get to $$(m, 0)$$ when
$$m$$ is divisible by $$2x_d$$. That is we can find integer solutions of $$\sum a_ix_i = n$$ and if we make
use of aforementioned identity we get $$x_d | n$$ and the greatest common divisor of $$2x_i$$ is obviously
$$2x_d$$. So $$2x_d | m$$. 

So given a target point $$(x_t, y_t)$$, it is reachable if and only if both $$x_t$$ and $$y_t$$ is divisible
by $$x_d$$ and $$y_d$$.

If one of $$\frac{x_i + y_i}{x_d}$$ is odd, then we can get a more strong conclution that we can also reach
$$(x_d, 0)$$ instead of $$(2x_d, 0)$$. because of $$(x_i + y_i) / x_d$$ is odd, we can conclude either
$$x_i / x_d$$ or $$y_i / x_d$$ is odd. Assume $$x_i / x_d$$ is odd. Then $$y_i / x_d$$ must be even.
Then we go to $$(x_i, y_i)$$ first, then as $$y_i / x_d$$ is even, we can go from that point by $$(0, -2x_d)$$
once a time until we meet $$(x_i, 0)$$. Consider all $$(x_i, 0)$$ are reachable, thus we can reach $$(x_d, 0)$$ now.

So the solution for this problem is:

1. For our target point $$(x_t, y_t)$$ is $$(0, 0)$$ we do not need to move, so it is reachable.
2. If either $$x_t$$ or $$y_t$$ can not be divided by $$x_d$$ it can not be reached.
3. If there is one $$(x_t + y_t) / x_d$$ is odd. Then any $$(x_t, y_t)$$ other than the former two cases is reachable.
4. At last, if $$(x_t + y_t) / x_d$$ is even, we can reach it.


