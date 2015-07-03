# Maximal Rectangle/Square

Given a matrix contains `1`s and `0`s. Find the maximal rectangle/square in the matrix that
contains all `1`s. The maximal square problem is a subset of maximal rectangle as all squares
are rectangles. So the maximal square must be contained in a maximal rectangle. So at first
we give the solution to Maximal rectangle.

A brute-force solution is iterate all sub rectangles in the matrix and check if they contains
all `1`s. This is a solution at $$O((NM)^3)$$'s time cost which is not acceptable.
We can preprocess the matrix to reduce it. let `DP[i][j]` saves the count of continus `1`s 
from `matrix[i][j]` to the cells above. It has a iteration formula:

{% math %}
DP[i][j]=\begin{cases}
DP[i-1][j]+1 & matrix[i][j]=1\\
0 & otherwise
\end{cases}
{% endmath %}

To calculate the area of rectangle whose right bottom point at `matrix[i][j]` and width is `w`, we can start from
`DP[i][j]` and let height `h` be the minimal value of `DP[i][j], DP[i][j - 1], ..., DP[i][j - w + 1]`.
Then the area equals $$w\times h$$. Iterate over all `i, j`, we can get the maximal area of rectangles
that contains all `1`s. Similarly, if we let side length to be `min(w, h)`, we will get the maximal square
that contains all `1`s. 

Below a solutions to related problem [Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/) and
[Maximal Square](https://leetcode.com/problems/maximal-square/).

```java
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int[] countLine = new int[matrix[0].length];
        
        int res = 0;
        int height = 0;
        int width = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    countLine[j]++;
                    height = countLine[j];
                    for (int k = j; k >= 0; k--) {
                        if (countLine[k] == 0) break;
                        else {
                            height = Math.min(height, countLine[k]);
                            width = Math.min(j - k + 1, height);
                            res = Math.max(res, width * width);
                        }
                    }
                } else countLine[j] = 0;
            }
        }
        return res;
    }
}
```

```java
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int[] countLine = new int[matrix[0].length];
        
        int res = 0;
        int height = 0;
        int width = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    countLine[j]++;
                    height = countLine[j];
                    for (int k = j; k >= 0; k--) {
                        if (countLine[k] == 0) break;
                        else {
                            height = Math.min(height, countLine[k]);
                            width = j - k + 1;
                            res = Math.max(res, width * height);
                        }
                    }
                } else countLine[j] = 0;
            }
        }
        return res;
    }
}
```

The time cost of two solutions are both $$O(NM^2)$$, they are good enough but there are
a way based on the solution to [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
that can reduce the time cost to $$O(NM)$$. This solution dropped the third inner loop on `k`
and replace it with a $$O(M)$$ cost loop parallel to that loop of `j`.

The space cost is $$O(M)$$ in code above as well as the $$O(NM)$$ solution.
