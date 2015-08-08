import java.io.*;
import java.util.*;

public class CleaningShifts {
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

    private static void solve(int T, int[][] cows) {
        int L = 1;
        while (L < T) L <<= 1;
        int[] rmq = new int[L * 2 - 1];
        Arrays.fill(rmq, Integer.MAX_VALUE);

        Arrays.sort(cows, new Comparator<int[]>() {
            public int compare(int[] a, int b[]) {
                if (a[0] != b[0]) return a[0] - b[0];
                else return a[1] - b[1];
            }
        });

        for (int i = 0; i < cows.length; i++) {
            int l = cows[i][0], r = cows[i][1], s = cows[i][2];
            int rangemin = 0;
            if (l > 0) rangemin = query(rmq, l - 1, r - 1, 0, 0, L - 1);

            if (rangemin < Integer.MAX_VALUE) {
                set(rmq, r, rangemin + s, 0, 0, L - 1);
            }
        }

        int min = rmq[L + T - 2];
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(), M = in.nextInt(), E = in.nextInt();
            int T = E - M + 1;
            int[][] cows = new int[N][3];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    cows[i][j] = in.nextInt();
                }
                cows[i][0] -= M;
                cows[i][1] -= M;
            }

            solve(T, cows);
        }
    }
}
