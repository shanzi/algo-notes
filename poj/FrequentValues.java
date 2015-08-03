import java.io.*;
import java.util.*;

public class FrequentValues {
    private static int L;
    private static int[] maxtree;
    private static int[] lefttree;
    private static int[] righttree;

    private static void initTreeRange(int[] array, int k, int kl, int kr) {
        if (kl >= kr) return;

        int m = (kl + kr) / 2;
        int cl = k * 2 + 1;
        int cr = k * 2 + 2;
        initTreeRange(array, cl, kl, m);
        initTreeRange(array, cr, m + 1, kr);

        int leftCount = maxtree[cl];
        int rightCount = maxtree[cr];
        int overCount = 0;

        lefttree[k] = lefttree[cl];
        righttree[k] = righttree[cr];

        if (m + 1 < array.length && array[m] == array[m + 1]) {
            overCount = righttree[cl] + lefttree[cr];
            if (array[kl] == array[m + 1]) lefttree[k] += lefttree[cr];
            if (kr < array.length && array[kr] == array[m]) righttree[k] += righttree[cl];
        }

        maxtree[k] = Math.max(Math.max(leftCount, rightCount), overCount);
    }

    private static void initTrees(int[] array) {
        L = 1;
        while (L < array.length) L <<= 1;

        maxtree = new int[L * 2 - 1];
        lefttree = new int[L * 2 - 1];
        righttree = new int[L * 2 - 1];

        for (int i = 0; i < array.length; i++) {
            maxtree[i + L - 1] = 1;
            lefttree[i + L - 1] = 1;
            righttree[i + L - 1] = 1;
        }

        initTreeRange(array, 0, 0, L - 1);
    }

    private static int query(int[] array, int k, int kl, int kr, int l, int r) {
        if (l > kr || r < kl) return 0;
        else if (l <= kl && r >= kr) {
            return maxtree[k];
        } else {
            int m = (kl + kr) / 2;
            int cl = k * 2 + 1;
            int cr = k * 2 + 2;

            int leftCount = query(array, cl, kl, m, l, r);
            int rightCount = query(array, cr, m + 1, kr, l, r);
            int overCount = 0;

            if (m >= l && m <= r && m + 1 < array.length && array[m] == array[m + 1]) {
                overCount += Math.min(m - l + 1, righttree[cl]);
                overCount += Math.min(r - m, lefttree[cr]);
            }

            return Math.max(Math.max(leftCount, rightCount), overCount);
        }
    }

    private static void solve(int[] array, int[][] query) {
        initTrees(array);
        for (int i = 0; i < query.length; i++) {
            System.out.println(query(array, 0, 0, L - 1, query[i][0], query[i][1]));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int N = scanner.nextInt();
            if (N == 0) break;
            int Q = scanner.nextInt();

            int[] array = new int[N];
            int[][] query = new int[Q][2];

            for (int i = 0; i < N; i++) array[i] = scanner.nextInt();
            for (int i = 0; i < Q; i++) {
                query[i][0] = scanner.nextInt() - 1;
                query[i][1] = scanner.nextInt() - 1;
            }

            solve(array, query);
        }
    }
}
