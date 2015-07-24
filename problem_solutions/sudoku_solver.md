# Sudoku Solver

Classic problem. At first we put all empty cells into a list, then we recursively test every possible
configuration of each empty cell to find a solution. The number of empty cells is at most 81. So there
won't be a `StackOverflowError`.

```java
public class SudokuSolver {
    private boolean checkValid(char[][] board, int x, int y, int n) {
        // check row
        for (int i = 0; i < 9; i++) if ((board[x][i] - '0') == n) return false;
        
        // check column
        for (int i = 0; i < 9; i++) if ((board[i][y] - '0') == n) return false;
        
        // check block
        int l = (x / 3) * 3;
        int t = (y / 3) * 3;
        
        for (int i = l; i < l + 3; i++) {
            for (int j = t; j < t + 3; j++) {
                if ((board[i][j] - '0') == n) return false;
            }
        }
        
        return true;
    }
    
    private boolean solveSudoku(char[][] board, List<Integer> xpos, List<Integer> ypos, int index) {
        if (index >= xpos.size()) return true;
        
        int x = xpos.get(index);
        int y = ypos.get(index);
        
        for (int i = 1; i <= 9; i++) {
            if (checkValid(board, x, y, i)) {
                board[x][y] = (char)('0' + i);
                if (solveSudoku(board, xpos, ypos, index + 1)) return true;
                board[x][y] = '.';
            }
        }
        
        return false;
    }
    
    public void solveSudoku(char[][] board) {
        ArrayList<Integer> xpos = new ArrayList<Integer>();
        ArrayList<Integer> ypos = new ArrayList<Integer>();
        
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y] == '.') {
                    xpos.add(x);
                    ypos.add(y);
                }
            }
        }
        
        solveSudoku(board, xpos, ypos, 0);
    }
}
```
