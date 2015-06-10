import java.util.*;

public class CtuRobots {
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private void sortCap(int[] cap, int[] cost) {
        for (int i = 0; i < cap.length; i++) {
            for (int j = i + 1; j < cap.length; j++) {
                if (cap[i] > cap[j]) {
                    swap(cap, i, j);
                    swap(cost, i, j);
                }
            }
        }
    }
    public double maxDist(int B, int[] cost, int[] cap) {
        sortCap(cap, cost);
        double[][] dp = new double[cost.length + 1][B + 1];

        for (int i = 1; i <= cost.length; i++) {
            for (int j = 1; j <= B; j++) {
                if (cost[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            cap[i - 1] * 0.5 + dp[i - 1][j - cost[i - 1]] / 3.0
                            );
                }
            }
        }
        return dp[cost.length][B];
    }
}

