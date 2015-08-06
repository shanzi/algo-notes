import java.io.*;
import java.util.*;

public class CornFields {
    private static long MOD = 100000000;
    private static void solve(int N, int M, int[] rows) {
        if (N == 0 || M == 0) {
            System.out.println(1);
            return;
        }
        int W = (1 << M) - 1;
        long[] last = new long[1 << M];
        long[] cur = new long[1 << M];

        last[0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= W; j++) {
                if (last[j] == 0) continue;

                for (int k = 0; k <= W; k++) {
                    if ((k & rows[i]) == 0 && (j & k) == 0 && (k & (k << 1)) == 0) {
                        cur[k] = (cur[k] + last[j]) % MOD;
                    }
                }
            }

            long[] tmp = last;
            last = cur;
            cur = tmp;
            Arrays.fill(cur, 0);
        }

        long res = 0;
        for (int i = 0; i < last.length; i++) {
            res = (res + last[i]) % MOD;
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(); int M = in.nextInt();
            int[] rows = new int[N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (in.nextInt() == 0) rows[i] |= (1 << j);
                }
            }
            solve(N, M, rows);
        }
    }
}
