# Dead Fraction (POJ 1930)

This is a very classical question. You are given a number in decimal respresentation and one of more
decimal places in the tail of decimal part may be infinite repeated. You are asked to return the neareast
fraction to that decimal. To be conventient, here we only considers decimal that is between 0.0 and 1.0.
Let the number be denoted as $$n = 0.abcdefgh$$, assume the last $$c$$ decimal places is repeating and we
can see $$l$$ decimal place in total.

Assume $$c = 4$$, we have $$n\cdot 10^l = abcdefgh.efgh\cdots$$ and $$n\cdot 10^{l - c} = abcd.efgh\cdots$$, 
as $$efgh$$ are infinite repeating, so the decimal part of those two number are exactly the same,
if we discount the second from the first, we will get:

$$
n\cdot 10^l - n\cdot 10^{l - c} = abcdefgh.efgh\cdots - abcd.efgh\cdots = abcdefgh - abcd
$$

To get the original $$n$$, we can transform the equation into:

$$
n = \frac{abcdefgh - abcd}{10^l - 10^{l - c}}
$$

For $$\forall c \in [1, l]$$ the formal above always holds. So we can use GCD method to reduce the fraction on the
right.  As the question statement does not give us the exact value of $$c$$, so we iterate $$c$$
from $$1$$ to $$l$$ and pick the one with lowest denominator as answer.

!CODEFILE "../poj/DeadFraction.java"
