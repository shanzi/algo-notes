# gWheels

We have three groups of cogs. Cogs in the same group rotate at the same radian rate as they all have the
same center and be bound together. Now we connect the three groups with two chains. One from the first to 
the second, one from the second to the third. The two chains can not be connected to the same cog in the 
second group.

Now if we regard the rotation rate of the first cog group to be 1, depends on different configurations of chains,
we can get different rate of the last cog group, which is a fractional number $$p/q$$. Given the size of each cogs
of the three groups, you are asked to decide if a specified fraction is able to be achieved.

We know that if the size of the cog in the first group is $$a$$, it is connected to cog of size $$x$$ in the second
group and the size of the cog in the last group is $$b$$ and it is connected to a cog of size $$y$$. Then we have

$$
\frac{p}{q} = \frac{a}{x}\frac{y}{b} \Rightarrow \frac{p\cdot y}{q\cdot x} = \frac{a}{b}
$$

So first we use two loop to decide $$\frac{p\cdot y}{q\cdot x}$$ and find if there exists $$a$$ and $$b$$
respectively in that two groups to make such a fraction. Also, we have to use `gcd` function to find the 
greatest common divisor and simplify the fraction. To fastly check the the existens, we first sort sizes of cogs
in each group and once we get that fraction $$\frac{p\cdot y}{q\cdot x}$$ in its simplest representation,
we multiply the numerator and denominator by 1, 2, 3 and so on until one of them exceed the range of cogs.
Each time, we use binary search to check if we can find such $$a$$ and $$b$$.

An other method might be that we iterate every possible combinations of sizes from the first and last cog groups
and saved the in a `HashSet` or put them into an array and sort them. Now we can just use the set or binary search
to check existence.

!CODEFILE "../apac/2016/RoundB/gWheels.java"
