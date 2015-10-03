import java.io.*;
import java.util.*;

public class AlbocedeDNA {
    static private int MOD = 1000000007;
    static private int[][][] dp = new int[512][512][4];
    private static int solve(String s) {
        for (int i = 0; i < 512; i++) {
            for (int j = 0; j < 512; j++) {
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] = 0;
                }
            }
        }
        char[] str = s.toCharArray();
        int n = str.length;
        for (int i = 1; i <= n; i++) {
            char c = str[i - 1];
            if (c == 'a') {
                for (int j = i; j >= 0; j--) {
                    dp[j + 1][0][0] = (dp[j + 1][0][0] + dp[j][0][0]) % MOD;
                }
                dp[1][0][0] = (dp[1][0][0] + 1) % MOD;
                dp[1][0][0] = (dp[1][0][0] + dp[0][0][3]) % MOD;
            } else if (c == 'b') {
                for (int j = 0; j < i; j++) {
                    for (int k = i; k >= 0; k--) {
                        dp[j][k + 1][1] = (dp[j][k + 1][1] + dp[j][k][1]) % MOD;
                    }
                    dp[j][1][1] = (dp[j][1][1] + dp[j][0][0]) % MOD;
                }

            } else if (c == 'c') {
                for (int k = 0; k < i; k++) {
                    for (int j = 0; j < i; j++) {
                        dp[j][k][2] = (dp[j][k][2] + dp[j + 1][k][2]) % MOD;
                        dp[j][k][2] = (dp[j][k][2] + dp[j + 1][k][1]) % MOD;
                    }
                }
            } else {
                for (int j = 0; j < i; j++) {
                    for (int k = 0; k < i; k++) {
                        dp[j][k][3] = (dp[j][k][3] + dp[j][k + 1][3]) % MOD;
                        dp[j][k][3] = (dp[j][k][3] + dp[j][k + 1][2]) % MOD;
                    }
                }
            }
        }
        return dp[0][0][3];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int t = 0; t < N; t++) {
            System.out.printf("Case #%d: %d\n", t + 1, solve(in.next()));
        }
    }
}
