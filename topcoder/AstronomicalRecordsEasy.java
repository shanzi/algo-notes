public class AstronomicalRecordsEasy {
    private int LCS(int[] A, int[] B, int a, int b, int[][] dp) {
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                dp[i][j] = Math.max(
                        dp[i - 1][j - 1] + (A[i - 1] * b == B[j - 1] * a ? 1 : 0),
                        Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[A.length][B.length];
    }

    public int minimalPlanets(int[] A, int[] B) {
        int max = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int a = A[i], b = B[j];

                max = Math.max(max, LCS(A, B, a, b, dp));
            }
        }
        return A.length + B.length - max;
    }
}

