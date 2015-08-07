import java.io.*;
import java.util.*;

public class RectangularCovering {
    private static void makeMasksAndAreas(int[][] points, int[][] masks, int[][] areas) {
        int N = points.length;
        for (int a = 0; a < N; a++) {
            for (int b = a + 1; b < N; b++) {

                int left = Math.min(points[a][0], points[b][0]);
                int right = Math.max(points[a][0], points[b][0]);
                int top = Math.max(points[a][1], points[b][1]);
                int bottom = Math.min(points[a][1], points[b][1]);

                if (left == right) right++;
                if (top == bottom) top++;

                areas[a][b] = (right - left) * (top - bottom);
                    
                for (int c = 0; c < N; c++) {
                    int x = points[c][0], y = points[c][1];
                    if (x >= left && x <= right && y >= bottom && y <= top) {
                        masks[a][b] |= 1 << c;
                    }
                }
            }
        }
    }
    
    private static void solve(int[][] points) {
        int N = points.length;
        int W = (1 << N);
        int[][] dp = new int[N][W];
        int[][] masks = new int[N][N];
        int[][] areas = new int[N][N];
        makeMasksAndAreas(points, masks, areas);

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = 0;

        for (int i = 1; i < N; i++) {
            for (int state = 0; state < W; state++) {
                if (dp[i - 1][state] == Integer.MAX_VALUE) continue;
                for (int a = 0; a < N; a++) {
                    for (int b = a + 1; b < N; b++) {
                        int mask = masks[a][b];
                        if ((mask | state) == state) continue;

                        dp[i][mask | state] = Math.min(dp[i][mask | state], dp[i - 1][state] + areas[a][b]);
                    }
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            res = Math.min(res, dp[i][W - 1]);
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            if (N == 0) return;

            int[][] points = new int[N][2];
            for (int i = 0; i < N; i++) {
                points[i][0] = in.nextInt();
                points[i][1] = in.nextInt();
            }

            solve(points);
        }
    }
}
