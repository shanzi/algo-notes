public class TaroGrid {
    public int getNumber(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();

        if (n == 0 || m == 0) return 0;

        int maxCount = 1; // please initialize it with a correct number

        for (int y = 0; y < m; y++) {
            int colCount = 1;
            for (int x = 1; x < n; x++) {
                if (grid[x].charAt(y) == grid[x - 1].charAt(y)) {
                    colCount++;
                } else {
                    colCount = 1;
                }

                if (colCount > maxCount) maxCount = colCount;
            }
        }

        return maxCount;
    }
}
