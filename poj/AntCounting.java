import java.io.*;
import java.util.*;

public class AntCounting {
    static private int MOD = 1000000;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt(), A = in.nextInt(), S = in.nextInt(), B = in.nextInt();
        int[] count = new int[T];
        for (int a = 0; a < A; a++) {
            count[in.nextInt() - 1]++;
        }
        int[] dp = new int[B + 1];
        int[] last = new int[B + 1];
        dp[0] = last[0] = 1;
        for (int i = 0; i < T; i++) {
            int c = count[i];
            for (int j = 1; j <= B; j++) {
                dp[j] = (dp[j - 1] + last[j] - (j - c - 1 >= 0 ? last[j - c - 1] : 0) + MOD) % MOD;
            }
            int[]tmp = dp;
            dp = last;
            last = tmp;
        }
        int sum = 0;
        for (int i = S; i <= B; i++) {
            sum = (sum + last[i]) % MOD;
        }
        System.out.println(sum);
    }
}
