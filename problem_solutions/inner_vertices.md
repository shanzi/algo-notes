# Inner Vertices (POJ 1990)

Initially, there is some black and white vertices on a infinite grid. If he a white vertex is between two
black vertices both in the same row and column, it will become black. Asked to return the number of
black vertices at last.

At first we can find that this progress will not repeat infinitely as turn a white vertex to a black vertex
won't added other position that won't change to black before. That is, if a vertex turns from white to black,
then it is between two black vertices both in the same row and column. Then if another vertex turns from white
to black because of that before, it must be in the same row or column. But then it is also can be regarded to
be turn by that two black vertices that make the first turn. So mark a white vertice to black won't increase
the number of total black vertices at last.

The solution to this problem is a little complex. At first we need to sort the vertices according to their
coordinates. We will sort it by `x` first and then `y`. When a vertex comes, vertices with lower `x` coordinate
will have already been processed so we can use a binary indexed tree to query how many grid points should be
black from current `y` to last processed black vertex with the same `y`. Count all these black vertices we will
get the answer.

Note that we have to handle the first vertex we meet at some `y` coordinate correctly. Also, the coordinates space
is too large that we have to do some works to compress it.

!CODEFILE "../poj/InnerVertices.java"
