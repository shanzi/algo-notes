import java.io.*;
import java.util.*;

public class ParenthesesOrder {
    static private long INF = Long.MAX_VALUE / 2;
    static private long[][] table = new long[101][101];
    private static void makeTable() {
        for (int i = 0; i <= 100; i++) {
            table[0][i] = 1;
        }
        for (int i = 1; i <= 100; i++) {
            for (int j = i; j <= 100; j++) {
                if (table[i - 1][j] >= INF || table[i][j - 1] >= INF) table[i][j] = INF;
                else table[i][j] = Math.min(INF, table[i - 1][j] + table[i][j - 1]);
            }
        }
    }
    private static String solve(int n, long k) {
        int[] grid = new int[n + 1];
        Arrays.fill(grid, n);
        grid[n] = 0;
        k -= 1;
        for (int i = 1; i < n; i++) {
            grid[i] = grid[i - 1];
            int t = n - i;
            while (grid[i] > n - i && table[t - 1][grid[i]] <= k) {
                k -= table[t - 1][grid[i]];
                grid[i]--;
            }
        }
        if (k > 0) return "Doesn't Exist!";
        else {
            StringBuilder s = new StringBuilder(n * 2);
            int last = grid[0];
            for (int i = 1; i <= n; i++) {
                s.append('(');
                while (last > grid[i]) {
                    s.append(')');
                    last--;
                }
                last = grid[i];
            }
            return s.toString();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        makeTable();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            long k = in.nextLong();
            System.out.printf("Case #%d: %s\n", t + 1, solve(n, k));
        }
    }
}
