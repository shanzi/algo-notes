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

If the matrix can not be expanded as a sorted 1-D array. That is, we have every rows are ascending
as well as every columns are ascending, we can not apply the method above. We can come up with two soltuions.

Solution one is similar to binary search, each time we check the element at the center and find next region
to recursively search. According to element distributions in the matrix, we can only aboundon only about 25% 
of elements and have to recursively search on other 75%. Every time we skip 25% of remaining elements, so
the time complexity is about $$O(\log(M\times N))$$.

```java
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target, int l, int r, int t, int b) {
        if (l > r || t > b) return false;
        
        int midx = (l + r) / 2;
        int midy = (t + b) / 2;
        
        if (matrix[midy][midx] == target) return true;
        else if (matrix[midy][midx] > target) {
            return (searchMatrix(matrix, target, l, midx - 1, t, midy - 1) ||
                searchMatrix(matrix, target, l, midx - 1, midy, b) ||
                searchMatrix(matrix, target, midx, r, t, midy - 1));
        } else {
            return (searchMatrix(matrix, target, midx + 1, r, midy + 1, b) ||
                searchMatrix(matrix, target, midx + 1, r, t, midy) ||
                searchMatrix(matrix, target, l, midx, midy + 1, b));
        }
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        return searchMatrix(matrix, target, 0, n - 1, 0, m - 1);
    }
}
```

Another method is at first we search the target in the dialogonal of matrix with binary search method, then we either
will find the target or find the nearest elements to the target. Based on the nearest elements, we can sieve out
about half of items in the matrix in average, then at last we recursively search the part remains.
Note if the matrix or search region has only one row or one column, this method may degenerate to linear search
which will cost a lot of time. In these cases, we just apply binary search method. Every time we skip 50% of remaining
items and during each iteration, we need a binary search to find nearest elements. So the total time complexity
will become $$O(\log(M+N)\log(M\times N))$$.

```java
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target, int l, int r, int t, int b) {
        if (l > r || t > b) return false;
        
        int w = r - l;
        int h = b - t;
        
        if (w == 0) {
            while (t <= b) {
               int mid = (t + b) / 2;
               if (matrix[mid][l] == target) return true;
               else if (matrix[mid][l] < target) {
                   t = mid + 1;
               } else {
                   b = mid - 1;
               }
            }
            return false;
        }
        
        if (h == 0) {
            return (Arrays.binarySearch(matrix[t], l, r + 1, target) >= l);
        }
        
        int ll = 0;
        int rl = Math.min(w, h);
        
        while (ll <= rl) {
            int mid = (ll + rl) / 2;
            if (matrix[t + mid][l + mid] == target) return true;
            else if (matrix[t + mid][l + mid] < target) {
                ll = mid + 1;
            } else {
                rl = mid - 1;
            }
        }
        
        return (searchMatrix(matrix, target, l + ll, r, t, t + rl) ||
            searchMatrix(matrix, target, l, l + rl, t + ll, b));
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        return searchMatrix(matrix, target, 0, n - 1, 0, m - 1);
    }
}
```
