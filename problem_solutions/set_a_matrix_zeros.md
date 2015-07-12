# Set a Matrix Zeros

This is a simple problem. A straightforward way is to visit every position in the matrix and use
two array to record which row and column should be set zero. After that, we revisit every elements
and set those positions to be zero.

Below are a more space efficient solution. We uses the first row to record if a column should be zero
by setting a zero at that position. For rows below, we set it to zero when we find at least
one zero in that row. Note that we won't set a row to zeros immediately after finding the first zero,
we have to find all zeros in this row and mark them in the first row before we erase it.
Also we should find if the first row should be erased ahead of time.

```java
public class SetAMatriZeros {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        
        boolean firstRow = false, row = false;
        
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }
        
        for (int i = 1; i < matrix.length; i++) {
            row = false;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) continue;
                
                matrix[0][j] = 0;
                row = true;
            }
            
            if (row) {
                for (int k = 0; k < matrix[0].length; k++) {
                    matrix[i][k] = 0;
                }
            }
        }
        
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0; 
                }
            }
            
            if (firstRow) matrix[0][i] = 0;
        }
        
    }
}
```
