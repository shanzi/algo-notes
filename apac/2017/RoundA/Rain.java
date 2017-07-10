import java.io.*;
import java.util.*;

public class Rain {
    static int N;
    static int M;
    static int[][] H;
    static int[][] R;
    static int[][] V;

    static boolean shouldVisit(int x, int y, int h) {
        if (x < 0 || y < 0 || x >= N || y >= M) return true;
        return V[x][y] != h;
    }

    static boolean dfs1(int x, int y, int h) {
        if (x < 0 || y < 0 || x >= N || y >= M) return false;
        V[x][y] = h;

        if (H[x][y] >= h) return true;
        boolean flag = true;
        if (shouldVisit(x - 1, y, h)) flag = dfs1(x - 1, y, h) && flag;
        if (shouldVisit(x + 1, y, h)) flag = dfs1(x + 1, y, h) && flag;
        if (shouldVisit(x, y - 1, h)) flag = dfs1(x, y - 1, h) && flag;
        if (shouldVisit(x, y + 1, h)) {
            flag = dfs1(x, y + 1, h) && flag;
        }

        return flag;
    }

    static void dfs2(int x, int y, int h) {
        if (x < 0 || y < 0 || x >= N || y >= M) return;
        if (H[x][y] >= h || R[x][y] >= h) return;

        R[x][y] = Math.max(R[x][y], h);

        dfs2(x - 1, y, h);
        dfs2(x, y - 1, h);
        dfs2(x + 1, y, h);
        dfs2(x, y + 1, h);
    }

    static int solve() {
        for (int h = 1000; h > 0; h--) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (H[x][y] >= h || V[x][y] == h) continue;
                    if (dfs1(x, y, h)) {
                        dfs2(x, y, h);
                    }
                }
            }
        }

        int count = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                count += R[x][y] - H[x][y];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            N = in.nextInt();
            M = in.nextInt();
            H = new int[N][M];
            R = new int[N][M];
            V = new int[N][M];
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    H[x][y] = in.nextInt();
                    R[x][y] = H[x][y];
                }
            }
            System.out.printf("Case #%d: %d\n", t + 1, solve());
        }
    }
}
