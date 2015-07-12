# Most Divisors

Given an integer $$n$$, you are asked to find an integer less than $$n$$ with most divisors.
The least such number is asked to be return.

Divisors has two types: prime or not prime divisors. Total number of divisors is proportional
to number of distinct divisors in $$[1, \sqrt{n}]$$. To solve this problem, at first we find
as much as prime divisors less than $$\sqrt{n}$$. Then, based on this, we find as many as non
prime divisors as possible.

To find prime divisors, we uses sieve methods. To pick as many as prime divisors possible,
we apply greedy strategy. We always pick the most least $$k$$ prime divisors until the product
of all these divisors is larger than $$n$$. Then we start to find non-prime divisors.
As any muliple of a prime number won't be prime, so we can choose to multiply one of our
prime divisors to produce more divisor less than $$\sqrt(n)$$. We keep multiplying until
the product exceed $$n$$. Obviously, to be able to multiply as many times as possible,
we need to muliple the least divisor. So greedy strategy again, we always pick multiple of $$2$$
as a non-prime divisor.
