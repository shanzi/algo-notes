import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class DollarDayz {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), K = in.nextInt();
        BigInteger[] dp = new BigInteger[N + 1];
        dp[0] = new BigInteger("1");
        for (int i = 1; i <= N; i++) {
            dp[i] = new BigInteger("0");
        }
        for (int k = 1; k <= K; k++) {
            for (int i = k; i <= N; i++) {
                dp[i] = dp[i].add(dp[i - k]);
            }
        }
        System.out.println(dp[N]);
    }
}
