import java.util.*;
public class EmoticonsDiv1 {
    public int printSmiles(int smiles) {
        int[] dp = new int[smiles + 1];
        Arrays.fill(dp, 9999);
        dp[0] = 0; dp[1] = 0;

        for (int i = 2; i <= smiles; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + find(j, i));
            }
        }

        return dp[smiles];
    }

    private int find(int j, int i) {
        int count = 1 + (i - j) / j;
        if ((i - j) % j != 0) {
            count += 1 + j - (i - j) % j;
        }
        return count;
    }
}
