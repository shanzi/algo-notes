import java.io.*;
import java.util.*;

public class DNARepair {
    private static char[] AGCT = {'A', 'G', 'C', 'T'};

    private static int solve(String S, String[] P) {
        TreeSet<String> sortTree = new TreeSet<String>();
        for (String p : P) {
            for (int i = 0; i <= p.length(); i++) {
                sortTree.add(p.substring(0, i));
            }
        }

        int K = sortTree.size();

        String[] prefixes = new String[K];
        Iterator<String> iter = sortTree.iterator();
        for (int i = 0; i < K; i++) {
            prefixes[i] = iter.next();
        }

        boolean[] ng = new boolean[K];
        int[][] next = new int[K][4];
        for (int i = 0; i < K; i++) {
            String pfx = prefixes[i];
            for (String p : P) {
                if (p.length() <= pfx.length() && pfx.substring(pfx.length() - p.length()).equals(p)) {
                    ng[i] = true;
                    break;
                }
            }

            for (int j = 0; j < 4; j++) {
                String s = pfx + String.valueOf(AGCT[j]);
                for (int k = 0; k <= s.length(); k++) {
                    next[i][j] = Arrays.binarySearch(prefixes, s.substring(k));
                    if (next[i][j] >= 0) break;
                }
            }
        }

        char[] str = S.toCharArray();
        int[][] dp = new int[str.length + 1][K];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;

        for (int t = 0; t < str.length; t++) {
            Arrays.fill(dp[t + 1], Integer.MAX_VALUE);

            for (int i = 0; i < K; i++) {
                if (ng[i] || dp[t][i] > str.length) continue;

                for (int j = 0; j < 4; j++) {
                    int k = next[i][j];
                    dp[t + 1][k] = Math.min(dp[t + 1][k], dp[t][i] + ((str[t] == AGCT[j]) ? 0 : 1));
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < K; i++) {
            if (ng[i]) continue;
            ans = Math.min(ans, dp[str.length][i]);
        }

        if (ans > str.length) return -1;
        else return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = 1;
        while (in.hasNextInt()) {
            int N = in.nextInt();
            if (N == 0) return;

            String[] P = new String[N];
            for (int i = 0; i < N; i++) {
                P[i] = in.next();
            }
            String S = in.next();

            System.out.printf("Case %d: %d\n", num++, solve(S, P));
        }
    }
}
