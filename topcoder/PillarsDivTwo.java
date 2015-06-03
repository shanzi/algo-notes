public class PillarsDivTwo {
    public double maximalLength(int[] height, int w) {
        double[][] dp = new double[height.length][2];
        for (int i = 1; i < height.length; i++) {
            int t2t = Math.abs(height[i] - height[i - 1]);
            int t2b = Math.abs(height[i-1] - 1);
            int b2t = Math.abs(height[i] -1);

            double t2tl = Math.sqrt(t2t * t2t + w * w);
            double t2bl = Math.sqrt(t2b * t2b + w * w);
            double b2tl = Math.sqrt(b2t * b2t + w * w);
            double b2bl = w;

            dp[i][0] = Math.max(dp[i - 1][0] + t2tl, dp[i - 1][1] + b2tl);
            dp[i][1] = Math.max(dp[i - 1][0] + t2bl, dp[i - 1][1] + b2bl);
        }
        return Math.max(dp[height.length - 1][0], dp[height.length - 1][1]);
    }
}
