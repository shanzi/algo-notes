# Super 2048

This problem is not about advanced algorithms but implementation skills. The solution is simple,
we just simulate the process and give out the result. To do this conveniently, we use two position
mapping function to help map our access to matrix according to the direction to push the cells.
And we always push the matrix to the left in our push code and the mapping function will transform
our operation to the correct direction. To simplify the code and avoid mistakes, we first squash all
cells with numbers larger than zero to the target direction without merging them. Then, based on the result
we merge cells in couples if they are equal.

!CODEFILE "../apac/2015/RoundA/Super2048.java"
