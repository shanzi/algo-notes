# N-Queen

N-Queen problem is a typical recursion problem. Note that we use an int array which each element
stands for where the queen is at each row on the board. We also use a boolean array `used` to mark
which column has alread has a queen so that skip checking on the board state to save time.

To output every possible configuration:

```java
public class NQueen {
    private List<String> buildBoard(int[] config) {
        ArrayList<String> board = new ArrayList<String>(config.length);
        for (int i = 0; i < config.length; i++) {
            StringBuilder line = new StringBuilder();
            
            for (int j = 0; j < config.length; j++) {
                if (j == config[i]) line.append('Q');
                else line.append('.');
            }
            
            board.add(line.toString());
        }
        return board;
    }
    
    private boolean checkValid(int[] config, int pos, int i) {
        for (int j = 0; j < pos; j++) {
            if (Math.abs(config[j] - i) == Math.abs(j - pos)) return false;
        }
        return true;
    }
    
    private void solveNQueue(int[] config, boolean[] used, int pos, List<List<String>> result) {
        if (pos >= config.length) {
            result.add(buildBoard(config));
            return;
        }
        
        for (int i = 0; i < config.length; i++) {
            if (used[i]) continue;
            
            if (checkValid(config, pos, i)) {
                config[pos] = i;
                used[i] = true;
                solveNQueue(config, used, pos + 1, result);
                used[i] = false;
            }
        }
    }
    
    public List<List<String>> solveNQueens(int n) {
        ArrayList<List<String>> result = new ArrayList<List<String>>();
        int[] config = new int[n];
        boolean[] used = new boolean[n];
        solveNQueue(config, used, 0, result);
        return result;
    }
}
```

To output how many configurations are possible in total:

```java
public class NQueenII {
    private boolean checkValid(int[] config, int pos, int i) {
        for (int j = 0; j < pos; j++) {
            if (Math.abs(config[j] - i) == Math.abs(j - pos)) return false;
        }
        return true;
    }
    
    private int totalNQueens(int[] config, boolean[] used, int pos) {
        if (pos >= config.length) return 1;
        
        int count = 0;
        
        for (int i = 0; i < config.length; i++) {
            if (used[i]) continue;
            
            if (checkValid(config, pos, i)) {
                config[pos] = i;
                used[i] = true;
                count += totalNQueens(config, used, pos + 1);
                used[i] = false;
            }
        }
        
        return count;
    }
    
    public int totalNQueens(int n) {
        int[] config = new int[n];
        boolean[] used = new boolean[n];
        
        return totalNQueens(config, used, 0);
    }
}
```
