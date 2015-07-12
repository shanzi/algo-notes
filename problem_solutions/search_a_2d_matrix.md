# Search a 2D Matrix

If we put the rows of this matrix in a line from top to bottom, we will
find the elements in that array is aways increasing. So we just consider
it as a single array and apply binary search methods.

```java
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int l = m * n;
        
        int a = 0, b = l - 1, mid, val;
        
        while (a <= b) {
            mid = a + ((b - a) >> 1);
            val = matrix[mid / n][mid % n];
            
            if (val < target) {
                a = mid + 1;
            } else if (val > target) {
                b = mid - 1;
            } else {
                return true;
            }
        }
        
        return false;
    }
}
```
