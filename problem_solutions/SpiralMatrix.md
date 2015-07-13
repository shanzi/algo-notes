# Spiral Matrix

Spiral Matrix II is simpler, it asks you to generate a square spiral matrix.

To spirally fill a matrix, we uses a direction variable and try to fill
the matrix in order, until no empty cell left. Do forget to break out
the loop after a valid direction is picked.

```java
public class SpiralMatrix {
    final static int[] dx = {0, 1, 0, -1};
    final static int[] dy = {1, 0, -1, 0};
    
    
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if (n == 0) return matrix;
        
        int i = 0, j = 0, k = 0, d = 0;
        while (matrix[i][j] == 0) {
            matrix[i][j] = ++k;
            
            for (int c = 0; c < 4; c++) {
                if (i + dx[d] < 0 || i + dx[d] >= n || j + dy[d] < 0 || j + dy[d] >= n ||
                    matrix[i + dx[d]][j + dy[d]] != 0) {
                        d = (d + 1) % 4;
                } else {
                    i += dx[d];
                    j += dy[d];
                    break;
                }
            }
        }
        
        return matrix;
    }
}

```

To visit a matrix in spiral order is a little harder as we can only do this in
place. It is hard to mark visited cells but we can always visit the cells layer by layer.
We have to take care of those matrix with only one row or one col.

```java
public class SpiralMatrix {
    final static int[] dx = {0, 1, 0, -1};
    final static int[] dy = {1, 0, -1, 0};
    
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (matrix.length == 0 || matrix[0].length == 0) return result;
        
        int n = matrix.length;
        int m = matrix[0].length;
        
        int half = Math.min(m, n) / 2;
        
        int layer = 0;
        while (layer < half) {
            for (int i = layer; i <= m - layer - 1; i++) {
                result.add(matrix[layer][i]);
            }
            
            for (int i = layer + 1; i < n - layer - 1; i++) {
                result.add(matrix[i][m - layer - 1]);
            }
            
            for (int i = m - layer - 1; i > layer; i--) {
                result.add(matrix[n - layer - 1][i]);
            }
            
            for (int i = n - layer - 1; i > layer; i--) {
                result.add(matrix[i][layer]);
            }
            
            layer++;
        }
        
        if (n <= m && (n & 1) == 1) {
            for (int i = layer; i < m - layer; i++) result.add(matrix[layer][i]);
        } else if (n > m && (m & 1) == 1) {
            for (int i = layer; i < n - layer; i++) result.add(matrix[i][layer]);
        }
        
        return result;
    }
}
```
