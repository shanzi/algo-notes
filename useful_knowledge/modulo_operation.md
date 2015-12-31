# Modulo Operation

Modulo operation is an important operation for programming. This operation is usually represented as $$\%$$ or `MOD`.
We meet this operation when the number range of a problem is to large and we need return the real result `MOD` some
value to avoid overflow when performing integer calculation. In some cases, the modulo operation even won't change
the final result. Some encryption algorithms use modulo heavily. So the characters and operation rules of
modulo is very important.

In this chapter, we will mainly talk about operation rules of modulo.

## Operation Rules

Operation rules about modulo is necessary knowledge for writing a program that `MOD` the intermediate and final results.
The following are common operation rules of modulo:

1. Distributive laws: 

    $$\begin{align*}
    (a + b) \bmod p &= (a \bmod p + b \bmod p) & \bmod p \\
    (a - b) \bmod p &= (a \bmod p - b \bmod p) & \bmod p \\
    (a \times b) \bmod p &= (a \bmod p \times b \bmod p) & \bmod p \\ 
    a^b \bmod p &= {(a \bmod p)}^{b \bmod p} & \bmod p 
    \end{align*}$$
        
2. Associative laws:

    $$\begin{align*}
    \big((a + b) \bmod p + c\big) \bmod p &= \big(a + (b+c)\bmod p\big) \bmod p  \\
    \big((a \times b) \bmod p \times c\big) \bmod p &= \big(a \times (b \times c)\bmod p\big) \bmod p  \\
    \end{align*}$$

3. Distributive law with multiplication:

   $$\begin{align*}
    \big((a + b) \bmod p \times c\big) \bmod p &= \big( (a \times c) \bmod p + (b \times c)\bmod p\big) \bmod p  \\
    \end{align*}$$

Note that the division operation do not have the laws above.

## Computation Cost

Modulo computation is often thought to be not as efficient as multiplication or addition operation,
but it still depends on hardware design and CPU structure. We scarcely need optimize an operation
like module, but sometimes we can just replace it with a simple and straight change.

For example, for the code below:

```java
// a is int[10]
int i = 0;
while(true) {
    a[i] = i;         // an example operation
    i = (i + 1) % 10; // increase i without index out of range
}
```

A better implementation might be:

```java
// a is int[10]
int i = 0;
while(true) {
    a[i] = i; // an example operation
    i++;
    if (i >= 10) i -= 10;
}
```

The second piece of code often runs faster than the first one.
