# Cow Acrobats (POJ 3045)

Give a series of cows with their weight and strength and let them stand as a stack. The risk
if a cow in the stack equals the total weight of cows stand on top if it. You are asked to minimize
the greatest risk among all cows by ordering them.

This is a problem with binary search that is a little hard. The key is to find a way to verify if
there exists a way to ordering the cow to let the greatest risk to be less than or equal to guessed
value by binary search.

The solution also needs application of greedy strategy. At first we sort the cows according to `weight + strength`,
Then as for some subset of the cows, if we choose the cow `i` as bottom, the risk of it will be
total weight in the set minus `weight + strength` of cow `i`. Every time we choose the cow with lowest
`weight + strength` to maximize the risk but we require the risk is not greater than our guessed value.
If we go like this through every cow and find every time we always can find a cow to satisfied our restriction,
then the guess is feasible. Or if at a point we can not find such a cow, it is not feasible.

We can use `LinkedList` as queue to implement this greedy strategy. When we find a cow can be bottom cow,
we first prepend it to the head of the queue. When we meet some where current cow is not able to be used as bottom,
we poll the cow with lowest `weight + strength` from the queue and reduce the total weight until using current cow
as bottom is feasible. If finally the queue is empty but current cow is still not usable, the guessed risk value
is not feasible. As every cow comes into and out of the queue once respectively, the total time cost is
proportional to `N` in this part. As we need to sort the cow first and performing binary search, the total time cost
will be in $$O(N\log N)$$.

Note the minimum possible risk may be negative. So you should make sure the search range covers the negative part too.

!CODEFILE "../poj/CowAcrobats.java"
