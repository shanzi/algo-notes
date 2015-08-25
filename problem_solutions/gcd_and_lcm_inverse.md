# GCD & LCM Inverse (POJ 2429)

Given greatest common divisor and least common multiple, you are asked to find the origin two integers
and let the difference between the two is as small as possible.

Assume the two origin number is $$a$$ and $$b$$, then we have $$gcd(a, b) \mid lcm(a, b)$$, but
$$gcd(a, b) \nmid \frac{lcm(a, b)}{gcd(a, b)}$$. In fact we have $$lcm(a, b) = \frac{a\cdot b}{gcd(a,b)}$$.
Let $$m = gcd(a, b)$$, then there must have

$$
lcm(a, b) = \frac{a\cdot b}{m} = \frac{mx\cdot my}{m} = xy\cdot m
$$

While $$x$$ and $$y$$ are divisor of $$a$$ and $$b$$ and they are relatively-prime with each other.
So we first get $$xy = \frac{lcm(a, b)}{gcd(a, b)}$$ to get the multiplication of $$x$$ and $$y$$,
then we start from $$\lfloor\sqrt{xy}\rfloor$$ to iterately find for $$x$$ and thus we get $$y$$.
We stop iteration when the first time we find a pair $$x$$ and $$y$$ that are relatively-prime.
Then we return $$mx$$ and $$my$$.

!CODEFILE "../poj/GCDLCMInverse.java"
