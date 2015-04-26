import java.util.Arrays;

public class SpellCards {
    public int maxDamage(int[] level, int[] damage) {
        int n = level.length;
        int[] dp = new int[55];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int max = 0;

        for (int j = 0; j < n; j++) {
            for (int i = n; i > 0; i++) {
                int v = i - level[j];
                if (v < 0) break;
                if (dp[v] == -1) continue;

                dp[i] = Math.max(dp[i], dp[v] + damage[j]);
                if (dp[i] > max) max = dp[i];
            }
        }

        return max;
    }
}
