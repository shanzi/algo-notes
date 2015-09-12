import java.io.*;
import java.util.*;

public class CubeIV {
    static private int[][] M = new int[1010][1010];
    static private boolean[] next = new boolean[1010 * 1010];
    static private int[] dp = new int[1010 * 1010];
    static private int[] dx = {-1, 0, 1, 0};
    static private int[] dy = {0, 1, 0, -1};
    private static String solve(int N) {
        for (int i = 1; i <= N * N; i++) {
            next[i] = false;
            dp[i] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cur = M[i][j];
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k], y = j + dy[k];
                    if (x >= 0 && x < N && y >= 0 && y < N && M[x][y] == cur + 1) {
                        next[cur] = true;
                        break;
                    }
                }
            }
        }
        int max = 1;
        int pos = 1;
        for (int i = N * N; i >= 1; i--) {
            if (next[i]) dp[i] += dp[i + 1];
            if (max <= dp[i]) {
                max = dp[i];
                pos = i;
            }
        }
        return String.format("%d %d", pos, max);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    M[i][j] = in.nextInt();
                }
            }
            System.out.printf("Case #%d: %s\n", t + 1, solve(N));
        }
    }
}
