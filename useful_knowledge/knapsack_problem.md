# Knapsack Problem

Knapsack Problem is a very common problem on algorithm. Usually we use Dynamic Programming
methods to solve this kind of problems. The DP solution to this problems is said
to be pseudo-polynomial as the time cost is generally related to the sum of weights
or value, whose number of different discrete value may be very large.

Knapsack Problem has many sub-problems. Usually we only care about a subset of those
simplier ones. In this section we will discuss some of them in depth.

## 0-1 Knapsack Problem

0-1 Knapsack Problem is probably the easiest kind of similar problems. The problem
description is:

> There are $$n$$ objects, the $$i$$th object's weight is $$w_i$$, value is $$v_i$$,
> Then we pick some objects and put them into a knapsack. The total weight of the objects
> in the knapsack is required not to be more than $$W$$. 
>
> Find the picking plan with maximum total value in knapsack.

We start with a simple solution to this problem and then upgrade the solution step by step
to a good enough one for coding interview.

### Brute Force Solution

At first we could use a so-called *Brute Force* solution. This solution examined all
possible picking plan and find the one with maxium value. Recursion is a good way to
implement similar solution. For each object $$i$$, we consider two possible arrangement

1. put it into the knapsack if possible
2. do not put it into the knapsack

So it will spawn two branch for each objects. After decide the state of current object,
we do the same to the next object recursively.

Example codes:
```java
// input n, W, w[n], v[n]
int n, W;
int w[n], v[n];

int recur(int i, int j) {
    int res;
    if (i == n) {
        // no objects left
        return 0;
    } else if (j < w[i]) {
        // this object can not be put into the knapsack
        res = recur(i + 1, j);
    } else {
        res = Math.max(
                recur(i + 1, j),  // do not pick current object
                recur(i + 1, j - w[i]) + v[i]); // pick current object, j - w[i] capacity left
    }
    return res;
}
```

This solution's time cost is $$O(2^n)$$, which means it will be extremely slow when $$n$$ is very large.
The Brute Force solution usually won't be good enough for a coding interview.

A better solution might be pruning some of the branches during recursion.

### Recursion with purning

It is obviously that in the last solution, we were doing some calculation unnecessary, which slows down
the algorithm a log. For example, for $$i, j$$ we might have calculated it serveral time, but if $$i, j$$
is the same, the return of function is the same. We can use a matrix to cache the intermediate results.

```java
int recur(int i , int j, int[][]cache) {
    if (cache[i][j] >= 0) {
        // elements of cache is initialized as -1,
        // if the value is higher than -1, it has already been calculated
        return cache[i][j];
    }
    int res;
    if (i == n) {
        // no objects left
        return 0;
    } else if (j < w[i]) {
        // this object can not be put into the knapsack
        res = recur(i + 1, j);
    } else {
        res = Math.max(
                recur(i + 1, j),  // do not pick current object
                recur(i + 1, j - w[i]) + v[i]); // pick current object, j - w[i] capacity left
    }
    cache[i][j] = res;
    return res;
}
```

This solution performs quite good. Its time complexity is $$O(nW)$$, where $$W$$ is count of all possible
distinct total weight. But the space cost is a little high as it is using recursion to traverse over
objects. When $$n$$ is very large, the program will even terminated with a StackOverflow error.
A recursion with pruning branches solution might be enough in many situations, but we want better,
don't we?

What if we abondon recursion and use loop to solve the problem? Yes, by doing this we will get
a classical DP solution.

## DP solution

From the recursion solution above, we can easily get the recursive formula of our DP solution.
Let $$dp_{i,j}$$ denotes the maximum value possible to pick from objects $$i, i+1, i+2, \cdots$$ to put into a knapsack
with capacity $$j$$. Then we know $$dp_{0, j}$$ will be the solution to our problem. for any object $$i$$, we have

{% math %}
dp_{i,j} = \left\{ 
    \begin{array}{l l}
         dp_{i+1,j} & \quad j < w_i\\
        \max \{dp_{i+1, j}, dp_{i+1,j-w_i}\} + v_i & \quad \text{otherwise}
    \end{array} \right.
{% endmath %}

And initially, we have $$dp_{n,j} = 0$$. To calculate the result conveniently, we loop $$i$$ from the end
back to $$0$$. An implementation is like below:

```java
int dp[MAX_N + 1][MAX_W+1]; // DP matrix

int solve() {
    for (int i = n - 1; i >= 0; i--) {
        for (int j = 0; j <= W; j++) {
            if (j < w[i]) {
                dp[i][j] = dp[i + 1][j];
            } else {
                dp[i][j] = Math.max(
                    dp[i + 1][j],
                    dp[i + 1][j - w[i]] + v[i]);
            }
        }
    }
    return dp[0][W];
}
```

Other than the backward solution above, we can also come up with a forward solution.
Let $$dp_{i+1}{j}$$ stand for the maximum value possible to pick objects from
the first $$i$$ objects with the weight is no more than $$j$$. Then we have $$dp_{0,j}$$
and

{% math %}
dp_{i + 1,j} = \left\{ 
    \begin{array}{l l}
         dp_{i,j} & \quad j < w_{i}\\
        \max \{dp_{i, j}, dp_{i,j-w_i}\} + v_{i} & \quad \text{otherwise}
    \end{array} \right.
{% endmath %}

The implementation will be:

```java
int solve() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= W; j++) {
            if (j < w[i]) {
                dp[i + 1][j] = dp[i][j];
            } else {
                dp[i + 1][j] = Math.max(
                    dp[i][j],
                    dp[i][j - w[i]] + v[i]);
            }
        }
    }
    return dp[n][W];
}
```

The backward and forward solution are both quite good. Generally you just need to know one of them
when faced similar problems. 0-1 Knapsack Problem is the easiest kind of Knapsack Problem, So it
has the highest probability to appear among or similar problems. This kind of problem is one
of the must-master algorithm problems.

## Unbounded Knapsack Problem

Unbounded Knapsack Problem is another type of Knapsack Problem. The difference between this
type of problem and 0-1 Knapsack Problem is that, every objects in the problems can be picked
unlimited times. A more clear description is:

> There are $$n$$ kinds of objects, number of each kind's objects is unlimited or nearly unlimited.
> Pick from some objects to put into the knapsack with the total weight of picked object no more
> than the knapsack's capacity $$W$$. Found the picking plan with highest total value.

Note that the number of the objects for one kind need not to be really unbounded. In fact,
as long as the number of objects is large enough to fill the knapsack is ok. So one obvious
solution is that, we repeat each kind of object for enough times and uses a method for
0-1 Knapsack Problem to solve this problem.

A better way is we come up with a new recursive formula and simplify it.
Follow our forward solution to 0-1 Knapsack Problem, let $$dp_{i+1,j}$$ to be
the maximum total value to pick objects from the first $$i$$ objects without the total
weight exceed $$W$$. We have $$dp_{0,j} = 0$$ and:

{% math %}
dp_{i + 1,j} = \max \{dp_{i, j - k \times w_i} + k \times v_i \; | \; \forall k \ge 0, j \ge k \times w_i\}
{% endmath %}

This recursive is good enough to give a implementation whose time complexity is $$O(nW^2)$$, but
it is still not the best solution we know. The recursive formula can be further transformed:

{% math %}
\begin{align}
dp_{i + 1,j} & = \max \{dp_{i, j - k \times w_i} + k \times v_i \; | \; \forall k \ge 0, j \ge k \times w_i\} \\
& = \max \{ dp_{ij}, \max \{dp_{i, j - k \times w_i} + k \times v_i \; | \; \forall k \ge 1, j \ge k \times w_i\}\} \\
& = \max \{ dp_{ij}, \max \{dp_{i, (j - w_i) - k \times w_i} + k \times v_i \; | \; \forall k \ge 0, j - w_i \ge k \times w_i\} + v_i\} \\
& = \max \{ dp_{ij}, dp_{i+1, j-w_i} + v_i \}
\end{align}
{% endmath %}

So we do not need a loop for $$k$$ any more, the corresponding implementation is:
```java
int solve() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= W; j++) {
            if (j - w[i] < 0) {
                dp[i + 1][j] =  dp[i][j];
            else {
                dp[i + 1][j] = Math.max(
                    dp[i][j],
                    dp[i + 1][j - w[i]] + v[i]);
            }
        }
    }
    return dp[n][j];
}
```

## Optimize Space

The implementations above is really good enough for a situation like coding interview, in which
you usually will be nervous and can not do your best. If you do not have solid confident, you
need not to give more optimized solutions.

And yes, there still are more optimized implementations compared with those previously given,
both for 0-1 Knapsack Problem and Unbounded Knapsack Problem. The new implementation is based on
the observation that the loop in the program will only access to rows numbered $$i-1$$ and
columns $$ < j$$. So we can reduce the matrix to be an array where `dp[j]` holds the original `dp[i-1][j]`,
and we can always carefully decide the order of loop `j` so that `dp[j - w[i]]` holds the value
we want.

The optimized solution for 1-0 Knapsack Problem:
```java
int dp[MAX_W + 1];

int solve() {
    for (int i = 0; i < n; i ++) {
        for (int j = W; j >= w[i]; j--) {
            dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i])
        }
    }
    return dp[W];
}
```

The optimized solution for Unbounded Knapsack Problem:
```java
int dp[MAX_W + 1];

int solve() {
    for (int i = 0; i < n; i++) {
        for (int j = w[i]; j <= W; j++) {
            dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
        }
    }
    return dp[W];
}
```

Reuse the same array to reduce the matrix to be an array to save space is a common technique
for Dynamic Programming. But the reduced code is usually not as apparent and easy to explain
as the original. Think twice before you'd like to use this technique during an interview!
