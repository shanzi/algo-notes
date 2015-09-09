import java.io.*;
import java.util.*;

public class NewYearsEye {
    private static double solve(int B, int L, int N) {
        double[][] dp = new double[L][L];
        double[][] last = new double[L][L];
        last[0][0] = 750.0 * B;
        for (int l = 1; l < L; l++) {
           for (int i = 0; i < l; i++) {
               for (int j = 0; j <= i; j++) {
                   if (last[i][j] > 250.0) {
                       double share = (last[i][j] - 250.0) / 3.0;
                       dp[i][j] += share;
                       dp[i + 1][j] += share;
                       dp[i + 1][j + 1] += share;
                   }
               }
           } 

           double[][] tmp = last;
           last = dp;
           dp = tmp;

           for (int i = 0; i <= l; i++) {
               for (int j = 0; j <= i; j++) {
                   dp[i][j] = 0.0;
               }
           }
        }

        int id = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j <= i; j++) {
                if ((++id) == N) {
                    return Math.min(250.0, last[i][j]);
                }
            }
        }
        return 0.0;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int B = in.nextInt(), L = in.nextInt(), N = in.nextInt();
            System.out.printf("Case #%d: %.7f\n", t + 1, solve(B, L, N));
        }
    }
}
