# gCube

The question is equal to given a series of large value and give a range. You are asked to multiply all these
range and as to get the high dimension `D` cube's edge length, we have to get it's `D`th root.

There are two key point in this question: the calculation of `n`th root with high precision and calculation the
multiplication of a large number which even exceeds the range of `double` type.

To calculate the `n`th root of a number `num`, other than implementing our own newton's method, a more
convenient way might be using a formular $$\sqrt[n]{num} = num^{\frac{1}{n}}$$ so that we can calculate
by something like `Math.pow(num, 1/n)`. But directly calculate some number's $$\frac{1}{n}$$th power
works bad if `n` is very big. An alternative is applying $$num^{\frac{1}{n}} = e^{\frac{\ln{num}}{n}}$$.
That will satisfied the precision requirements.

As for the second problem, actually we are not required to calculate the muliplication of all those numbers,
we are asked to calculate $$\sqrt[n]{A_1, A_2, \cdots, A_n}$$. It is equals to that we calculate the
`n`th root first before multiplying them. That is:

$$
\sqrt[n]{A_1\cdot A_2\cdots A_n} = \sqrt[n]{A_1}\cdot \sqrt[n]{A_2}\cdots \sqrt[n]{A_n}
$$

So that we can get the result without calculate a multiplication that exceeds range without losing precision.
One should keep this method in mind as many times we will need to do similar formular transformation to
enable big number calculation without using something like `BigDecimal`.

!CODEFILE "../apac/2016/RoundA/GCube.java"
