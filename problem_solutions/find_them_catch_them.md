# Find them, Catch them

A tyical uses of union find. To solve this type of problem, we have two different approach.
One is re-encode the relationships. Let's say we have two class `A` and `B`, once given two
people `a` and `b` can not be in the same class, that means either `a in A and b in B` and
`b in A and a in B` is true. We use `a-A`, `a-B`, `b-A` and `b-B` to stands for each containing
states. Then if two states should appears at the same time, we union them in union find set.
For a people numbered `i` and total people count `N`, we use `0 ~ N - 1` to stands for `i-A`,
`N ~ 2N - 1` to stands for `i-B`, then we can query relationship or union two state easily.
This solution is easily to understand and implment and applys to multiple classes questions.
Also this solution is not vulnerable to `StackOverflowError`. But it will cost more spaces.

!CODEFILE "../poj/FindThemCatchThem.java"

Another solution is that we uses rank values to mark if current node is in the same class of it's parent
node. If it is the same, we use `0`, or we use `1`. Then when we are peforming find and union operation,
we should handle and update the rank values correctly. In this implementation, we have to use recursive
way to perform find operation and after find we point current node directly to its final root.
The new rank is decided by current rank and it's old parent's new rank. As after the recursion,
currently the parent node is directly point to root. So parent node `p`, root node `rt` and current node `c`
has the following relationships:

1. `p` and `rt` in same clas, `c` and `p` in same class, then `c` and rt in same class
2. `p` and `rt` not in same clas, `c` and `p` not in same class, then `c` and rt in same class
3. Otherwise, `c` and `rt` not in same class

The relationships above can be represented with a consice expression: `(rank[c] + rank[p]) % 2`.

Also, when perform union, we have to handle similar things too. Let `p` and `q` be two roots of
two elements to union, the corresponding expression will be `(rank[p] + rank[q] + 1) % 2`.
This solution is a little hard to understand and not easy to be implemented right, it is also
vulnerable to `StackOverflow`. But this solution can be inducted to muliple classes questions and also
more save in space.

!CODEFILE "../poj/FindThemCatchThem2.java"
