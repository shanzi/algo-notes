public class CoinReversing {
    public double expectedHeads(int N, int[] a) {
        double[][] combcount = new double[N + 1][N + 1];
        for (int i = 0; i < combcount.length; i++) {
            combcount[i][0] = 1;
            combcount[i][i] = 1;
        }
        for (int i = 1; i < combcount.length; i++) {
            for (int j = 1; j <= i / 2; j++) {
                combcount[i][j] = (i - j + 1) * combcount[i][j - 1] / j;
            }
            for (int j = i / 2 + 1; j < i; j++) {
                combcount[i][j] = combcount[i][i - j];
            }
        }
        double[][] dp = new double[a.length][N + 1];
        dp[0][a[0]] = 1;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] == 0) continue;
                for (int k = 0; k <= a[i + 1] && k <= j; k++) {
                    int out = a[i + 1] - k;
                    int outbase = N - j;

                    if (out > outbase) continue;
                    double p = (combcount[j][k] * combcount[outbase][out]) / combcount[N][a[i + 1]];
                    dp[i+1][j - k + out] += dp[i][j] * p;
                }
            }
        }
        double res = 0;
        for (int i = 0; i <= N; i++) {
            res += dp[a.length - 1][i] * (N - i);
        }
        return res;
    }
}
