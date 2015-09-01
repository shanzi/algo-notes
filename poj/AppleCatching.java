import java.io.*;
import java.util.*;

public class AppleCatching {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt(), W = in.nextInt();
        int[] dp = new int[W + 1];
        for (int t = 1; t <= T; t++) {
            int tree = in.nextInt() - 1;
            for (int i = Math.min(W, t); i > 0; i--) {
                dp[i] = Math.max(dp[i] + ((i & 1) == tree ? 1 : 0), dp[i - 1] + 1);
            }
            dp[0] += (tree ^ 1);
        }
        int max = 0;
        for (int m : dp) {
            max = Math.max(max, m);
        }
        System.out.println(max);
    }
}
