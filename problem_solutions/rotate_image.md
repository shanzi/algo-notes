# Rotate Image

Rotate a matrix clockwisely for 90 degree. Two ways to do this.

Let the vertical axis to be `X` and horizontal axis to be `Y`, the size of matrix is `n`.
After rotation, the value of a cell `x, y` will go to `n - y, x`. To achieve this,
we can first flip the matrix arround the diagonal from top left to bottom right and get `y, x`.
Then we flip parallel `Y` in the middle of the matrix to get `n - y, x`.

```java
public class RotateImage {
    private void swap(int[][] matrix, int ax, int ay, int bx, int by) {
        int t = matrix[ay][ax];
        matrix[ay][ax] = matrix[by][bx];
        matrix[by][bx] = t;
    }
    
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return;
        
        for (int y = 0; y < n; y++) {
            for (int x = y + 1; x < n; x++) {
                swap(matrix, x, y, y, x);
            }
        }
        
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n / 2; x++) {
                swap(matrix, x, y, n - x - 1, y);
            }
        }
    }
}
```

Conversely, if we'd like to rotate counterclockwisely, we need to transform `x, y` to `y, n - x`.
So first we get `y, x` like what we do above, then, in the last step we flip the matrix parallel to `X`
axis in the middle.

There are another way to do such rotation. Take clockwise as example, value at `x, y` will go to
`n - y, x`, value at `n - y, x` will go to `n - x, n - y`, value at `n - x, n - y` will go to
`y, n - x` and value at `y, n - x` will go to `x, y`.

To apply this swap cycle in place, we can first move `x, y` to `y, n - x`, then to `n - x, n - y`
and finally get to `n - y, x`. After three swaps like this, we will get all these four values in
where it should be at last.

```java
public class RotateImage {
    private void swap(int[][] matrix, int ax, int ay, int bx, int by) {
        int t = matrix[ay][ax];
        matrix[ay][ax] = matrix[by][bx];
        matrix[by][bx] = t;
    }
    
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return;
        
        for (int y = 0; y < n / 2; y++) {
            for (int x = y; x < n - y - 1; x++) {
                swap(matrix, x, y, y, n - x - 1);
                swap(matrix, y, n - x - 1, n - x - 1, n - y - 1);
                swap(matrix, n - x - 1, n - y - 1, n - y - 1, x);
            }
        }
    }
}
```
