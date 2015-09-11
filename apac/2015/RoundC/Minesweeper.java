import java.io.*;
import java.util.*;

public class Minesweeper {
    static private int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static private int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    private static void countMines(int N, char[][] grid) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] != '.') continue;
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    if (i + dx[k] >= 0 && j + dy[k] >= 0 && i + dx[k] < N && j + dy[k] < N) {
                        count += (grid[i + dx[k]][j + dy[k]] == '*') ? 1 : 0;
                    }
                }
                grid[i][j] = (char)('0' + count);
            }
        }
    }
    private static void fillBFS(int N, int x, int y, char[][] grid) {
        LinkedList<Integer> xq = new LinkedList<Integer>();
        LinkedList<Integer> yq = new LinkedList<Integer>();

        xq.addLast(x);
        yq.addLast(y);

        while (!xq.isEmpty()) {
            int i = xq.pollFirst();
            int j = yq.pollFirst();

            char c = grid[i][j];

            grid[i][j] = '.';
            if (c != '0') continue;

            for (int k = 0; k < 8; k++) {
                if (i + dx[k] >= 0 && j + dy[k] >= 0 && i + dx[k] < N && j + dy[k] < N) {
                    char nc = grid[i + dx[k]][j + dy[k]];
                    if (nc >= '0' && nc <= '8') {
                        xq.addLast(i + dx[k]);
                        yq.addLast(j + dy[k]);
                    }
                }
            }
        }
    }
    private static int solve(int N, char[][] grid) {
        countMines(N, grid);
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == '0') {
                    fillBFS(N, i, j, grid);
                    count++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] >= '0' && grid[i][j] <= '8') count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            char[][] grid = new char[N][N];
            for (int i = 0; i < N; i++) {
                String l = in.next();
                for (int j = 0; j < N; j++) {
                    grid[i][j] = l.charAt(j);
                }
            }
            System.out.printf("Case #%d: %d\n", t + 1, solve(N, grid));
        }
    }
}
