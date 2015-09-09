import java.io.*;
import java.util.*;

public class CardGame {
    private static int solve(int[] array, int N, int K) {
        int[][] DP = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                DP[i][j] = j - i + 1;
            }
        }
        for (int r = 2; r < N; r++) {
            for (int l = r - 2; l >= 0; l--) {
                int min = DP[l][r];
                for (int m = l; m < r; m++) {
                    min = Math.min(min, DP[l][m] + DP[m + 1][r]);
                }
                for (int m = l + 1; m < r; m++) {
                    if (array[l] + K != array[m] || array[m] + K != array[r]) continue;
                    int lmin = DP[l + 1][m - 1];
                    int rmin = DP[m + 1][r - 1];
                    if (lmin == 0 && rmin == 0) {
                        min = 0;
                    } 
                }
                DP[l][r] = min;
            }
        }
        return DP[0][N - 1];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), K = in.nextInt();
            int[] array = new int[N];
            for (int i = 0; i < N; i++) {
                array[i] = in.nextInt();
            }
            System.out.printf("Case #%d: %d\n", t + 1, solve(array, N, K));
        }
    }
}
