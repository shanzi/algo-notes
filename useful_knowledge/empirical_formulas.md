# Empirical Formulas

Here are some empirical formulas that may be useful when faced with interview questions
or algorithm problems. These formulas are mostly from *Programming Pearls* and it is
recommended that the readers have a look at related content and exercises in that book.

## Little's Law

Simply speaking, the law can be described as:

> The average number of objects over time in a system, equals the average rate the objects leave 
> the system minus the average time period each object stays in the system.

Expending it into a mathematical formula we can say that:

$$\overbrace{N}^{\text{average number of objects}} = \overbrace{L}^{\text{leaving rate}} \times \overbrace{T}^{\text{average serving time}}$$

is true. This law is usually used to estimate the either one of the three parameters when knowing
the other two.

## Rule of 72

This rule is used to estimate an exponential growing variable's doubling time. Let's say some variable will grow by $$r\%$$ in unit time and it grows for $$y$$ unit time, then, when $$r\times y\approx 72$$, 
we have the original variable's value will be nearly just doubled.

When $$r%$$ is between $$5\%$$ and $$10\%$$, the error of Rule of 72 won't be higher than $$1\%$$. There are some similar rules such as Rule of 69 and so on described on [Wiki](http://en.wikipedia.org/wiki/Rule_of_72).

## $$\pi$$ seconds is a nano century

Yes, this formula is very simple: $$\pi$$ seconds is a nano ($$10^{-9}$$) century. This formula is useful
when estimating some program's runing time. Often it will be a bad program as the running time may be near
to tens of years. You should remember to come up with a better algorithm after estimated the time.

## Fermi Estimate

Fermi Estimate or [Fermi Problem](https://en.wikipedia.org/wiki/Fermi_problem) is an useful
method to estimate the amount of something with other related assumptions and known values.
The estimation usually need a [dimensional analysis](https://en.wikipedia.org/wiki/Dimensional_analysis) to
check the correction. Fermi Estimate can be used to verify if some data provided by a report or advertisement is believable.

It is highly recommended by *Programming Pearls* that everyone should learn themselves this method and
have some exercises. It will be useful in daily life.