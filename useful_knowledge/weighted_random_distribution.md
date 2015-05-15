# Weighted Random Distribution

About this topic, [this article](http://www.electricmonk.nl/log/2009/12/23/weighted-random-distribution/)
gives quite a detailed description. This chapter is going to rephrase some of the description of it
and rewrite the sample code into Java.

## Preface

If we'd like to randomly choose an element in an array, and each element has the same probability
to be chosen, then we just generate a random int $$i \in [0, N)$$ where $$N$$ is the number of elements,
and use $$i$$ as the index to access the element in the array. This is called *uniform distribution*.

But what if we'd like the elements have different likelihood to be chosen? That is weighted random
distribution problem. Let's say we want the item with hight weight value more likely to be chosen
and the probability is a linear function of weight. In other words, if item A has weight $$1$$ and
item B has weight $$2$$, then the item B is twice as likely to be chosen as item A. 

The solution to this problem depends on the actual demand of the problem and properties of
the weight value. Before we start to solve the problem, we should take notice of the following
few questions:

1. How many items to be randomly chosen from?
2. Do the members of item list change frequently?
3. Does weight value of an item change frequently?
4. How much extra space we can use?
5. How is the precision of weight value? That is, is the value an integer or decimal?
if it is a float number, how many decimal spaces is allowed?

Based on different answers to these questions, the different solution is most suitable.

## Precision of weight value

Let's talk about precision or number of decimal spaces of weight value. The first thing
we need to do is to uniform all the value into integer. Why we need to do this?
That is because our random number generated is ususally integer, if we'd like to use
this integer random value to pick element, we have to either translate the random number
to decimal or translate the weight value into integer.

But why we should translate the weight value instead of the converse? Because in
a computer system, some decimal number in base 10 is in fact not able to be represented
precisely in binary. So it is better to translate all the values into integer.

After the translation, we can set out to find methods to meet the demand.

## Expanding
