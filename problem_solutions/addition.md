# Addition

It is a quite senior problem. Given several facts of addition formulars such as `a+b=10`, `b+c=4` and `c+d=5` and so on,
you are asked to write a program that can give answer to any statements that can derive from the conditions given
without ambiguity. For example, from the instance above, we can also derive one more statement `a+d=11` apart
form the three equations given. Given these equations and some querys, we should out all statements we can get
without ambiguity.

As for the small data set we can just use DFS as we find that every equation and query only have plus operations
and from given equations we build a undirected graph. And we can get the sum of two numbers of there is a path
between the two numbers that contains odd number of edges. But this is not fast enough for large set.

To solve the large scale problem, we can use `Union-Find`. At first we can see if we encode the first variable
and the second variable with different encoding even if they are the same, we can use union find to check if
the statement a combination of two given names is derivable as the union find graph will be built as a bipartite
graph so any path from a node in group one to a node in group two must have odd number of edges.
Starts from this we can add extra fields to record the relationship and sum from a node to its parent.

Let's say we have two array `types` and `sums` that is corresponding to each node. We define when `types[i]` is `1`
`sums[i]` keeps the sum of `i` and its parent `ids[i]`, aka, `i+ids[i]=sums[i]`. Otherwise we keep `i-ids[i]` in
`sums[i]`. When perform find operation, we point the node to its root directly and at this time we must recursively
update `types[i]` and `sums[i]` carefully. Also, when we perform union operation of `x` and `y`, we have to
calculate their root parents `p` and `q`'s relationship and sum correctly too. The code here is need deep consideration
and is a little hard to implement correctly, but once finished we can check if the statement is valid and give the
result easily. For example, if `x` and `y` is connected, we know that there is a valid statement describing
`x+y`, also as we are building and querying a bipartite graph so the path from `x` to `y` must contains odd number
of edges. As the find operation pointed `x` and `y` to its parent, we must have either `types[x]` or `types[y]` is 1
and the other is 0. So for their common root parent `r`, we will get either `x+r` and `y-r` or `x-r` and `y+r`.
To get `x+y` we can just add `sum[x]` and `sum[y]`.

Also noted that their sometimes given some equations like `a+a=10` and `b+b=12`, by these two fomular we can actually
get the value `a` and `b` respectively and thus got results for equations like `a+b`. So don't remember to hander these
cases.

!CODEFILE "../apac/2015/RoundA/Addition.java"
