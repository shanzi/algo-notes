import java.io.*;
import java.util.*;

public class KAnonymouseSequence {
    private static int[] deq = new int[500005];
    private static long[] dp = new long[500005];
    private static long[] a = new long[500005];
    private static long[] S = new long[500005];

    private static long f(int j, int x) {
        return -a[j] * x + dp[j] - S[j] + a[j] * j;
    }

    private static boolean check(int f1, int f2, int f3) {
        long a1 = -a[f1], b1 = dp[f1] - S[f1] + a[f1] * f1;
        long a2 = -a[f2], b2 = dp[f2] - S[f2] + a[f2] * f2;
        long a3 = -a[f3], b3 = dp[f3] - S[f3] + a[f3] * f3;

        return (a2 - a1) * (b3 - b2) >= (b2 - b1) * (a3 - a2);
    }

    private static void solve(int n, int k) {
        for (int i = 0; i < n; i++) {
            S[i + 1] = S[i] + a[i];
        }

        int s = 0, t = 1;
        deq[0] = 0;
        dp[0] = 0;

        for (int i = k; i <= n; i++) {
            if (i - k >= k) {
                while(s + 1 < t && check(deq[t - 2], deq[t - 1], i - k)) t--;
                deq[t++] = i - k;
            }

            while (s + 1 < t && f(deq[s], i) >= f(deq[s + 1], i)) s++;

            dp[i] = S[i] + f(deq[s], i);
        }

        System.out.println(dp[n]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int T = in.nextInt();
            for (int i = 0; i < T; i++) {
                int n = in.nextInt(), k = in.nextInt();
                for (int j = 0; j < n; j++) {
                    a[j] = in.nextInt();
                }

                solve(n, k);
            }
        }
    }
}
