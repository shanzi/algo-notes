import java.io.*;
import java.util.*;

public class BalancedLineUp {
    private static int queryMax(int[] maxtree, int k, int l, int r, int kl, int kr){
        if (r < kl || l > kr) {
            return Integer.MIN_VALUE;
        } else if (l <= kl && r >= kr) {
            return maxtree[k];
        } else {
            return Math.max(
                    queryMax(maxtree, k * 2 + 1, l, r, kl, (kl + kr) / 2),
                    queryMax(maxtree, k * 2 + 2, l, r, (kl + kr) / 2 + 1, kr));
        }
    }

    private static int queryMin(int[] mintree, int k, int l, int r, int kl, int kr){
        if (r < kl || l > kr) {
            return Integer.MAX_VALUE;
        } else if (l <= kl && r >= kr) {
            return mintree[k];
        } else {
            return Math.min(
                    queryMin(mintree, k * 2 + 1, l, r, kl, (kl + kr) / 2),
                    queryMin(mintree, k * 2 + 2, l, r, (kl + kr) / 2 + 1, kr));
        }
    }

    private static void solve(int[] heights, int[][] ranges) {

        int len = 1;
        while (len < heights.length) len <<= 1;

        int[] maxtree = new int[len * 2 - 1];
        int[] mintree = new int[len * 2 - 1];

        Arrays.fill(maxtree, Integer.MIN_VALUE);
        Arrays.fill(mintree, Integer.MAX_VALUE);

        for (int i = 0; i < heights.length; i++) {
            maxtree[i + len - 1] = heights[i];
            mintree[i + len - 1] = heights[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            maxtree[i] = Math.max(maxtree[i * 2 + 1], maxtree[i * 2 + 2]);
            mintree[i] = Math.min(mintree[i * 2 + 1], mintree[i * 2 + 2]);
        }

        for (int i = 0; i < ranges.length; i++) {
            int l = ranges[i][0], r = ranges[i][1];
            System.out.println(queryMax(maxtree, 0, l, r, 0, len - 1) - queryMin(mintree, 0, l, r, 0, len - 1));
        }

    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        while (stdin.hasNextInt()) {
            int n = stdin.nextInt();
            int q = stdin.nextInt();

            int[] heights = new int[n];
            int[][] ranges = new int[q][2];

            for (int i = 0; i < heights.length; i++) {
                heights[i] = stdin.nextInt();
            }
            for (int i = 0; i < ranges.length; i++) {
                ranges[i][0] = stdin.nextInt() - 1;
                ranges[i][1] = stdin.nextInt() - 1;
            }

            solve(heights, ranges);
        }
    }
}
