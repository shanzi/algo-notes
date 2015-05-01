public class FlippingBitsDiv2 {
    public int getmin(String[] S, int M) {
        int len = 0;
        for (String s : S) {
            len += s.length();
        }
        int size = len / M;

        if (size == 0) return 0;

        int[] c = new int[size];
        int p = 0;
        for (String s : S) {
            for (char ch : s.toCharArray()) {
                if (ch == '1') c[p / M]++;
                p++;
            }
        }
        int[][] dp = new int[size][2];
        dp[0][0] = c[0] + 1;
        dp[0][1] = M - c[0];
        int[] flag = new int[size];
        flag[0] = 1;

        for (int i = 1; i < size; i++) {
            dp[i][0] = c[i] + Math.min(dp[i - 1][0], dp[i - 1][1] + 2);
            dp[i][1] = M - c[i] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            if (dp[i - 1][0] < dp[i - 1][1] + 2) flag[i] = flag[i - 1];
        }
        if (flag[size - 1] == 0) dp[size - 1][0]--;
        return Math.min(dp[size - 1][0], dp[size - 1][1]);
    }
}
