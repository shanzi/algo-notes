# Matrix (POJ 2155)

The binary value of a cell in the matrix equals to lowest bit of the number of times this cell has been reversed.
So we can uses a 2D binary indexed to count the times a cell has been reversed. The time cost of updating
a rectangular area is $$O(\log W \times \log H) where $$W$$ and $$H$$ is the width and height of the rectangle.
Query the binary value of a cell will cost $$(\log W\times \log H)$$ too. $$W$$ and $$H$$ in the latter
expression stands for the width and height of the whole matrix.

!CODEFILE "../poj/Matrix.java"
