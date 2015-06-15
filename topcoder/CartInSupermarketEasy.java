/* 
 * The total split times is limited to K, which means if we split at the mid point evertime,
 * the split times will exceed K. In other words, split different sequences in the same turn
 * is regarded as different splits and will be count respectively.
 * 
 * So we have to use DP method to solve this problem.
 *
 * Let DP[i][j] denotes the time cost to remove a sequence of i carts and we have j split count left.
 * Then, We have two options:
 *
 * 1. remove one from the sequence. Thus DP[i][j] = DP[i - 1][j] + 1.
 * 2. split the sequence into two part. After doing this, we will have j - 1 splits available in total
 *    but we can not let both the two subsequences to be divided by j - 1 times. Instead,
 *    it is the sum of split times of two subsequences to be (less than) j - 1.
 *    Thus we have to iterate over every possible split position and every split time limit assignment for
 *    two subsequences and to find a valid and minium one.
 *
 *    The time cost will be 1 + the max time to remove both subsequences. So the DP formula will be:
 *
 *    DP[i][j] = min(DP[i][j], max(dp[i - ii][kk], dp[i - ii][j - kk - 1]) + 1)
 *          while (ii = 1 to i - 1, kk = 0 to j - 1)
 */ 

public class CartInSupermarketEasy {
    public int calc(int N, int K) {
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = dp[i - 1][j] + 1;
                if (j > 0) {
                    for (int ii = 1; ii < i; ii++) {
                        for (int kk = 0; kk < j; kk++) {
                            dp[i][j] = Math.min(dp[i][j], Math.max(dp[ii][kk], dp[i - ii][j - kk - 1]) + 1);
                        }
                    }
                }
            } 
        }
        return dp[N][K];
    }
}
