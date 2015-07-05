# Number of Islands

We can use flood fill algorithm to solve this problem. The best way is using BFS instead of DFS
as DFS with recursive inplementation is vulnerable to `StackOverflowError`.
In the code below we use two queue to save `x` and `y` coordinates respectively.

```java
public class NumberOfIslands {
    final int[] xdelta = {1, -1, 0, 0};
    final int[] ydelta = {0, 0, 1, -1};
    
    private void BFS(char[][] grid, int i, int j) {
        LinkedList<Integer> xqueue = new LinkedList<Integer>();
        LinkedList<Integer> yqueue = new LinkedList<Integer>();
        xqueue.addLast(i);
        yqueue.addLast(j);
        
        while (!xqueue.isEmpty()) {
            int x = xqueue.pollFirst();
            int y = yqueue.pollFirst();
            if (grid[x][y] == '1') {
                grid[x][y] = '0';
                
                for (int d = 0; d < 4; d++) {
                    int nx = x + xdelta[d];
                    int ny = y + ydelta[d];
                    if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == '1') {
                        xqueue.addLast(nx);
                        yqueue.addLast(ny);
                    }
                }
            }
        }
    }
    
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    BFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
}
```
