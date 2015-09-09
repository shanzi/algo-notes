import java.io.*;
import java.util.*;

public class PasswordAttacker {
    static private long MOD = 1000000007;
    static private long[][] combs = new long[105][105];
    private static long pow(long a, long b) {
        long r = 1;
        while (b > 0) {
            if ((b & 1) == 1) r = (r * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return r;
    }
    private static void makeCombs() {
        long[] minverse = new long[105];
        for (int i = 1; i < 105; i++) {
            minverse[i] = pow(i, MOD - 2);
        }
        for (int n = 0; n < 105; n++) {
            combs[n][0] = 1;
            int mid = n / 2;
            for (int m = 1; m <= mid; m++) {
                combs[n][m] = ((combs[n][m - 1] * minverse[m]) % MOD * (n - m + 1)) % MOD;
            }
            for (int m = mid + 1; m <= n; m++) {
                combs[n][m] = combs[n][n - m];
            }
        }
    }
    private static void solve(int M, int N) {
        long[] dp = new long[N + 1];
        Arrays.fill(dp, 1);
        dp[0] = 0;
        for (int i = 1; i < M; i++) {
            for (int j = N; j > i; j--) {
                dp[j] = 0;
                for (int k = j - 1; k >= i; k--) {
                    dp[j] = (dp[j] + dp[k] * combs[j][k]) % MOD;
                }
            }
        }
        System.out.println(dp[N]);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        makeCombs();
        for (int t = 0; t < T; t++) {
            int M = in.nextInt(), N = in.nextInt();
            System.out.printf("Case #%d: ", t + 1);
            solve(M, N);
        }
    }
}
