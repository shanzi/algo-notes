# Protecting the Flowers (POJ 3262)

The key to this problem is not easy to find. The basic idea is we apply some measure on each cows
and pick one cow with least or highest value of that measure at a time. Let's say we have cow $$a$$ and $$b$$,
time to remove them are $$l_a$$ and $$l_b$$ and their damage abilities are $$d_a$$ and $$d_b$$.
If we remove $$a$$ first, the total damage of flowers are $$d_b\cdot l_a$$, otherwise it will be
$$d_a\cdot l_b$$. Obviously, if $$d_b\cdot l_a \le d_a\cdot l_b$$, we should remove $$a$$ first,
or we should remove $$b$$. Transform the formular we get

$$
\frac{l_a}{d_a} \le \frac{l_b}{d_b}
$$

That inspire us use $$\frac{l_i}{d_i}$$ as the measure to pick cows. In practice, we do not calculate
the fraction into double as it will lost precision. We use the first formular for comparation.

!CODEFILE "../poj/ProtectingTheFlowers.java"
