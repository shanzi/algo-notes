import java.util.*;

public class EmoticonsDiv2 {
    public int printSmiles(int smiles) {
        int[] dp = new int[smiles + 1];
        Arrays.fill(dp, 9999);
        dp[1] = 0;

        for (int i = 2; i <= smiles; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                }
            }
        }
        return dp[smiles];
    }
}
