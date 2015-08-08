import java.io.*;
import java.util.*;

public class MinimizingMaximizer {
    private static void set(int[] rmq, int i, int v, int k, int kl, int kr) {
        if (i >= kl && i <= kr) {
            rmq[k] = Math.min(rmq[k], v);

            if (kl >= kr) return;

            int mid = (kl + kr) >> 1;
            set(rmq, i, v, k * 2 + 1, kl, mid);
            set(rmq, i, v, k * 2 + 2, mid + 1, kr);
        }
    }
    
    private static int query(int[] rmq, int l, int r, int k, int kl, int kr) {
        if (l > kr || r < kl) return Integer.MAX_VALUE;
        else if (l <= kl && r >= kr) {
            return rmq[k];
        } else {
            int mid = (kl + kr) >> 1;

            return Math.min(
                    query(rmq, l, r, k * 2 + 1, kl, mid),
                    query(rmq, l, r, k * 2 + 2, mid + 1, kr));
        }
    }

    private static void solve(int N, int[][] sorters) {
        int L = 1;
        while (L < N) L <<= 1;
        int[] rmq = new int[L * 2 - 1];
        Arrays.fill(rmq, Integer.MAX_VALUE);

        set(rmq, 0, 0, 0, 0, L - 1);

        for (int i = 0; i < sorters.length; i++) {
            int l = sorters[i][0], r = sorters[i][1];
            int min = query(rmq, l, r, 0, 0, L - 1);

            if (min == Integer.MAX_VALUE) continue;

            set(rmq, r, min + 1, 0, 0, L - 1);
        }

        System.out.println(query(rmq, N - 1, N - 1, 0, 0, L - 1));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(), M = in.nextInt();
            int[][] sorters = new int[M][2];
            for (int i = 0; i < M; i++) {
                sorters[i][0] = in.nextInt() - 1;
                sorters[i][1] = in.nextInt() - 1;
            }

            solve(N, sorters);
        }
    }
}
