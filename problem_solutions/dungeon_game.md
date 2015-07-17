# Dungeon Game

This problem is suitable for a dynamic programing problem. Let $$DP[i][j]$$ be the minimum
HP the knight need to enter $$cell[i][j]$$ first and from that cell to reach he princess.
Of course to enter the right bottom cell where the princess stays we need at least $$\max{0, cell[i][j]}$$.
When performing dynamic programing iteration we ignore that at first the knight's HP must be at least `1`.
Then if the knight can start from entering $$cell[i][j]$$ and reach the target cell he must can also start from
$$cell[i][j]$$ to either $$cell[i][j + 1]$$ or $$cell[i + 1][j]. Then we can the following formular:

{% math %}
DP[i][j] = \max{0, \min{DP[i + 1][j], DP[i][j + 1]} - cell[i][j]}
{% endmath %}

We calculate $$DP[i][j]$$ from target cell back to start cell and then added `1` to $$DP[0][0]$$ to
get the final answer.

```java
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0 || dungeon[0].length == 0) return 1;
        
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        dungeon[m - 1][n - 1] = Math.max(0, -dungeon[m - 1][n - 1]);
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) continue;
                
                int bottom = (i + 1 >= m) ? Integer.MAX_VALUE : dungeon[i + 1][j];
                int right = (j + 1 >= n) ? Integer.MAX_VALUE : dungeon[i][j + 1];
                dungeon[i][j] = Math.min(bottom, right) - dungeon[i][j];
                dungeon[i][j] = Math.max(0, dungeon[i][j]);
            }
        }
        
        return dungeon[0][0] + 1;
    }
}
```
