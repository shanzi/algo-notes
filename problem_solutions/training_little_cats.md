# Training Little Cats (POJ 3735)

If we regard every operation on cats as matrix multiplication, all operations in a round can be
represented as a single matrix. To repeat `m` round is to calculate the `n`th power of that matrix.

The principle is simple but there are some problems that need to be overcomed.
At first, the result might be very large and exceed the range of `int` type so we have to use `long`.
Secondly, we have to improve the time efficiency of calculate matrix multiplication as the matrix may be
very large. After observation we find that the matrix of a single round is a sparse matrix as every row
at most has two non-zero elements. And after each mutiplication, this property won't change a lot.
So we should skip the zeros in the matrix when multiplying to reduce the time complexity from $$O(N^3)$$
to be very near to $$O(N^2)$$. Only after these two points is taken care of, we can pass the test case on POJ.

!CODEFILE "../poj/TrainingLittleCats.java"
