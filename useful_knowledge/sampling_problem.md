# Sampling Problem

Select several items from a collection by random or shuffle a sorted array are both belongs to
a kind of problem called Sampling Problem. There are several methods to handle this kind of problems,
each have different time cost and space cost.

As we all know, most computer systems can only generate pseudorandom numbers, in this chapter we
ignore this problem and assume the system call returns uniform random numbers.
The sample codes are all in Python and we will use Python's `random` package to provide
all functions needed.


## Choose $$m$$ out of $$n$$

We will begin with a common problem of choosing $$m$$ items from a set of size $$n$$.

### With probability

The first method uses probability, it iterates over each item and decide if we should
choose this item by a probability. After each iteration, the probability of choosing an item
will changed a bit and at last it increase to $$1$$. The code above shows how to do this:

```python
import random

select = m
remaining = n
for i in range(1, n):
    if random.randint(1, remaining) < select:
        print i
        select -= 1
    remaining -= 1
```

This method have a time cost of $$O(n)$$ in its worst case. But it will definitely finished
with desired number of elements.

### Shuffle the array and pick the first $$m$$

If an array of $$n$$ if totally shuffled and each order has the same probability to be presented.
Then the first $$m$$ element of this array might be a good solution to the $$m$$ out of $$n$$ problem.

The basic idea of shuffle an array is that we iterates through the whole array and swap the item at current
position with a random element after it. After $$n$$ times of swap, the array will be well shuffled and
one can prove that every order of items has equal presentation probability.

```python
array = range(n)

for i in range(n):
    # swap two element of an array
    swap(array, i, random.randint(0, n - 1))

res = array[:m]
```

The code above also shows the method to shuffle an array. This algorithm's time cost will always be
$$O(n)$$ and it also has a space cost of $$O(n)$$.

### Using a set

We use a `set` to maintain the items that has already been chosen, and only insert an item
if it is not in the set. Every time we generate a random number between $$0$$ and $$n$$ and do this repeatly until
we get enough elements. An example implementation:

```python
import random

S = set()
while len(S) < m:
    t = random.randint(1, n)
    if t not in S:
        S.add(t)
```

This method can terminated fast if $$m$$ is much less than $$n$$. The time cost will be much
if $$m$$ is quite near to $$n$$. In the worst case, this algorithm even won't terminated and the worst
case will actually happen on some type of machines as the pseudorandom numbers generated are not
really uniform.



### Using set without discard random numbers

There is a method to choose items with `set` without discarding random numbers generated.
This reduces the time complexity of algorithm. At the end of each interation, the algorithm
always insert an item into the set. If the current item has already in the set, it will insert
current iteration index.

The following code shows how to do it:

```python
import random

s = set()
for i in range(n - m + 1, n + 1):
    int t = random.randint(1, i) # generate a random int between 0 and i, including both end points.
    if t in s:
        s.add(t)
    else:
        s.add(i)
```

The analysis of the time cost depends on the implementation of the `set` datastructure. As we know, 
hash table's average search time is quite good, but in the worst case it will degraded to linear time.
A balanced tree can guarantee a time complexity of $$O(n)$$. Generally speaking, the time cost of
this method will be worse than $$O(m)$$, but it will be quite near.

This algorithm is named Floyd Algorithm.
A more detailed discussion of this algorithm will take place at secition **Floyd Algorithm** below.

### Pick an item from a list with unknown length

Sometimes we need to pick an element from a collection that we do not know the size of it, for example,
pick a single line from a big file or a online streaming source. The basic idea is, at first we choose
the first element, then when the $$k$$th line arrived, we override the current element with probability
as $$p = \frac{1}{k}$$. At the end of the file, the element left will be the element chosen.
It can be easily proven that each element has equal likelihood to be selected.

```python
import random

# f is a file with unknown length
selected = None
for i, l in enumerate(f.readlines(), 1):
    if random.randint(1, i) == i:
        selected = l
```

### Reservoir sampling

The solution above in fact has a name reservior sampling.

Reservoir sampling is a family of sampling algorithm to solve a class of problems that
the total set to sample from is very big or the size is not known when sampling is begin.

Other than the simplest instance of similar problems where we just need to choose one line,
another variation might be that exactly $$k$$ lines is required to be picked. Similar to
the one line version, at first we can pick the first $$k$$ lines by default.
And for a line $$i$$ after $$k$$, we choose it by a probability of $$\frac{k}{i}$$ and
replace one line from current chosen lines.

To be more clear, at line $$i$$ we generate a random number $$r$$ between $$1$$ and $$i$$,
if $$r \ge k$$ we replace the $$r$$th line in current selected lines. Otherwise, we do nothing.
A sample code might be like;


```python
import random

# f is a file with unknown length
selected = []

for i, l in enumerate(f.readlines(), 1):
    if i < k: selected.append(l)
    else:
        r = random.randint(1, i)
        if r < k:
            selected[r] = l
```

To prove this solution is correct, let's apply mathematic induction. Assume at the $$i$$th line
this solution is correct. That is for each line numbered from $$0$$ to $$i - 1$$ is chosen
with a probability of $$\frac{k}{i}$$. Then here come the $$(i + 1)$$th line. It is obviously
we will pick this line by a probability of $$\frac{k}{i + 1}$$. Then for each current chosen lines,
one might be chosen to be replaced at a probability of $$\frac{1}{k}$$.
So at the $$(i + 1)$$th line, one line chosen before has a probability of
$$\frac{k}{i + 1}\frac{1}{k}=\frac{1}{i + 1}$$ to be dropped. As after the $$(i + 1)$$th line
is processed, one line must not has  been replaced to be left and the total probability
will become:

{% math %}
\frac{1}{i}(1 - \frac{1}{i + 1}) = \frac{1}{i + 1}
{% endmath %}

That is, after the $$(i + 1)$$th line is processed, every line has a probability of $$\frac{1}{i + 1}$$
to be left in the chosen set. Thus the solution is correct that at last every line has the same likelyhood
to be chosen.

For more details about reservoir sampling algorithm please refer to
[Reservoir sampling](https://en.wikipedia.org/wiki/Reservoir_sampling).

## Floyd Algorithm

Floyd Algorithm is a series of methods of our $$m$$ out of $$n$$ method.
The last *using set without discard random numbers* method is an example of it.
We will talk about this method in detail and start from a simple implementation, keep upgrading
it and finally get a quite satisfied version. The discussion of this section also gives
a plain and simple scaffold for proving the correctness of this algorithm.

A elementary version of the Floyd Algorithm is using recursion. This version of algorithm will
be more easier to understand.

```python
import random

def sample(m, n):
    if m == 0: return set()
    s = sample(m - 1, n - 1)
    t = random.randint(1, n)
    if t not in s:
        s.add(t)
    else:
        s.add(n)
    return s
```

A more elegant and faster algorithm is like that given above, we uses loop instead of recursion.

```python
import random

s = set()
def sample(m, n):
    for i in range(n - m + 1, n + 1):
        int t = random.randint(1, i)
        if t in s:
            s.add(t)
        else:
            s.add(i)
    return s
```

The algorithm above might be the best with a `set` used to generate a sample or combination from
a larger collection. There are also a set of shuffling or arrangements problem we will meet.
The difference between a combination and a arrangement is that for a combination, the order
of the elements does not matter.

Apart from the shuffling algorithm mentioned above, there are also a method derived from that recursion verion
of Floyd algorithm. To achieve this, we just need replace the `set` with `list` and modify the insert operation
a little.

```python
import random

l = []
for i in range(n - m + 1, n + 1):
    t = random.randint(1, i)
    if t in l:
        l = [t] + l # prefix t to the head of l
    else:
        idx = l.index(t)
        l = l[:idx + 1] + [i] + l[idx + 1:] # insert t after where t is at
```

### A simple proof

The proof of Floyd algorithm in fact comes from the last method for generate arrangements.
It is based on the observation that for any arrangements generated, there are only one
random number sequence to generate it, so the algorithm can be backstepped. And if we assume
every sequence of random numbers has equal probability to present, then every arrangements
are generated with the same likelihood.

And, with the proof of the arrangement generation algorithm, we can also prove the correctness
of the combination generation algorithm. This conclusion is based on:

1. If the random number sequence are the same, the arrangement and combination generated by two
algorithms contains exactly the same items.
2. For any combinations of $$n$$ items, the number of arrangements of them are all $$n!$$.
3. So if the arrangement generation algorithm is correct, then the combination generation
algorithm is correct too.

