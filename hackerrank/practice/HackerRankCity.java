import java.io.*;
import java.util.*;

public class HackerRankCity {
    private static long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        long N = 1;
        long D = 0;
        long S = 0;
        for (int t = 0; t < T; t++) {
            int d = in.nextInt();

            /// (1)
            long N1 = (N + 1) % MOD;
            long D1 = (D + N * d) % MOD;
            long S1 = (S + D1) % MOD;

            /// (2)
            long N2 = (N1 * 2 - 1) % MOD;
            long D2 = (D1 * 2) % MOD;
            long S2 = (S1 * 2 + D1 * N1 % MOD) % MOD;
            
            /// N
            N = (N2 * 2) % MOD;
            D = (D2 * 2 + d * N2) % 2;
            S = (S2 * 2 + d * N2 * N2 % MOD + 2 * D2 * N2 % MOD) % MOD;
        }

        System.out.println(S);
    }
}
