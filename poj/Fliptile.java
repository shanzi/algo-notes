import java.io.*;
import java.util.*;

public class Fliptile {
    private static int getColor(int[][] face, int[][] flip, int x, int y) {
        int color = flip[x][y] + face[x][y];
        if (x - 1 >= 0) color += flip[x - 1][y];
        if (x + 1 < face.length) color += flip[x + 1][y];
        if (y - 1 >= 0) color += flip[x][y - 1];
        if (y + 1 < face[0].length) color += flip[x][y + 1];
        return color & 1;
    }
    private static int rowCount(int[][] face, int[][]flip, int row) {
        int N = face[0].length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (getColor(face, flip, row - 1, i) == 1) {
                flip[row][i]++;
                count++;
            }
        }
        return count;
    }
    private static int flipCalc(int[][]face, int[][]flip, int i, int M, int N) {
        int configCount = 0;
        for (int j = 0; j < N; j++) { 
            flip[0][j] = (i >> j) & 1;
            if (flip[0][j] == 1) configCount++;
        }
        for (int r = 1; r < M; r++) {
            Arrays.fill(flip[r], 0);
            configCount += rowCount(face, flip, r);
        }

        for (int j = 0; j < N; j++) {
            if (getColor(face, flip, M - 1, j) == 1) {
                return Integer.MAX_VALUE;
            }
        }
        return configCount;
    }
    private static void solve(int[][] face, int M, int N) {
        int[][] flip = new int[M][N];
        int minCount = Integer.MAX_VALUE;
        int minConfig = 0;
        for (int i = 0; i < (1 << N); i++) {
            int count = flipCalc(face, flip, i, M, N);
            if (count < minCount) {
                minCount = count;
                minConfig = i;
            }
        }
        if (minCount == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
        } else {
            flipCalc(face, flip, minConfig, M, N);
            for (int i = 0; i < M; i++) {
                System.out.printf("%d", flip[i][0]);
                for (int j = 1; j < N; j++) {
                    System.out.printf(" %d", flip[i][j]);
                }
                System.out.println();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        str = in.readLine().split(" ");
        int M = Integer.parseInt(str[0]), N = Integer.parseInt(str[1]);
        int[][] face = new int[M][N];
        for (int i = 0; i < M; i++) {
            str = in.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                face[i][j] = Integer.parseInt(str[j]);
            }
        }

        solve(face, M, N);
    }
}
