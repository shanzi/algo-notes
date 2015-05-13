import java.util.*;

public class ColorfulRoad {
    public int getMin(String road) {
        int n = road.length();
        if (n == 0) return 0;

        int[] dp = new int[n];
        Arrays.fill(dp, 10000);
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            char cur = road.charAt(i);
            char prev;
            switch (cur) {
                case 'R': 
                    prev = 'B';
                    break;
                case 'G': 
                    prev = 'R';
                    break;
                default:
                    prev = 'G';
            }

            for (int j = 1; i - j >= 0; j++) {
                if (road.charAt(i - j) == prev) {
                    dp[i] = Math.min(dp[i], dp[i - j] + j * j);
                }
            }
        }
        if (dp[n - 1] > n * n) {
            return -1;
        } else {
            return dp[n - 1];
        }
    }
}
