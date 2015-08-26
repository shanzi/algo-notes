import java.io.*;
import java.util.*;

public class ExtendedLightsOut {
    private static int M = 5;
    private static int N = 6;

    private static int[][] lights = new int[M][N];
    private static int[][] flip = new int[M][N];

    private static boolean isOn(int x, int y) {
        int count = lights[x][y] + flip[x][y];
        if (x - 1 >= 0) count += flip[x - 1][y];
        if (x + 1 < M) count += flip[x + 1][y];
        if (y - 1 >= 0) count += flip[x][y - 1];
        if (y + 1 < N) count += flip[x][y + 1];
        return (count & 1) == 1;
    }
    private static int calcRow(int row) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (isOn(row - 1, i)) {
                count++;
                flip[row][i]++;
            }
        }
        return count;
    }
    private static int calc(int i) {
        int count = 0;
        for (int j = 0; j < N; j++) {
            flip[0][j] = (i >> j) & 1;
            if (flip[0][j] == 1) count++;
        }
        for (int j = 1; j < M; j++) {
            Arrays.fill(flip[j], 0);
            count += calcRow(j);
        }

        for (int j = 0; j < N; j++) {
            if (isOn(M - 1, j)) return Integer.MAX_VALUE;
        }

        return count;
    }

    private static void solve() {
        int min = Integer.MAX_VALUE;
        int config = 0;

        for (int i = 0; i < (1 << 6); i++) {
            int count = calc(i);
            if (count < min) {
                min = count;
                config = i;
            }
        }

        calc(config);
        for (int i = 0; i < M; i++) {
            System.out.printf("%d", flip[i][0]);
            for (int j = 1; j < N; j++) {
                System.out.printf(" %d", flip[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    lights[i][j] = in.nextInt();
                }
            }
            System.out.printf("PUZZLE #%d\n", t + 1);
            solve();
        }
    }
}
