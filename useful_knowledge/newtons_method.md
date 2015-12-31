# Newton's Method

Newton's method is a iteration way to find zero points of $$f(x)$$. It is a widely used
numerical algorithm and has many application.

Let's say we have a function $$f(x)$$. We'd like to find a real solution $$x'$$ that let
$$f(x) = 0$$. To achieve that, we first make an initial guess of $$x_0$$ and start from it
we performing iteration based on:

$$
x_{k+1} = x_k - \frac{f(x_k)}{f'(x_k)}
$$

We repeat the iteration until desired precision is reached. That is $$|x_{k + 1} - x_{k}| < \epsilon$$
where $$\epsilon$$ is a very small float number. It can be shown that $$\lim_{k\rightarrow\infty}x_k=x'$$.

To show the application of Newton's method, we take *$$n$$th root algorithm* as an example.
To get $$n$$ root of a positive rational number $$A$$ is equivalent to find zero point for
$$f(x) = x^n - A$$. The derivative of $$f(x)$$ is:

$$
f'(x)=nx^{n-1}
$$

So we have:

$$
x_{k+1} = x_k - \frac{f(x_k)}{f'(x_k)}
 = x_k - \frac{x_k^n - A}{n x_k^{n-1}}
 = \frac{1}{n} \left[{(n-1)x_k +\frac{A}{x_k^{n-1}}}\right]
$$

Once we can find the $$n$$th root of a rational number, we can implement program to get number's rational power.
As any rational number can be represented as fraction of two integer $$\frac{p}{q}$$. So we can transform
a number $$A$$'s power like:

$$
A^{\frac{p}{q}} = A^a\cdot A^{\frac{b}{q}} = A^a\cdot \left(A^{\frac{1}{q}}\right)^b
$$

Where $$a = \left\lfloor \frac{p}{q}\right\rfloor$$ and $$b$$ is the remainder of $$\frac{p}{q}$$. Hence we can
transform ration power into calculation of two integer power and one $$n$$th root.

Newton's method is converging quite fast. It has square convergence which means after each iteration
the number of effective digits can be doubled. So we can generally regard our $$n$$th root program
to be a $$O(\log N)$$ algorithm. However, there are cases in which this method may not converge at all.

For more detailed discussion of this algorithm, refer to [Wikipedia](https://en.wikipedia.org/wiki/Newton%27s_method).