import java.io.*;
import java.util.*;

class Coin implements Comparable<Coin>{
    int x;
    int y;
    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int compareTo(Coin that) {
        Coin a = this;
        Coin b = that;
        return ((a.x == b.x) ? a.y - b.y : a.x - b.x);
    }
}

public class Coins {
    private static void solve(Coin[] coins, int m) {
        int[] dp = new int[m + 1];
        Arrays.fill(dp, 2000);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            Coin c = coins[i];
            for (int j = c.x; j <= m; j++) {
                dp[j] = Math.min(dp[j - c.x] + 1, dp[j]);
            }
            for (int j = 0; j <= m; j++) {
                if (dp[j] <= c.y) dp[j] = 0;
                else dp[j] = 2000;
            }
        }
        int count = 0;
        for (int i = 1; i <= m; i++) {
            if (dp[i] == 0) count++;
        }
        System.out.println(count);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int n = in.nextInt(), m = in.nextInt();
            if (n == 0 && m == 0) return;
            Coin[] coins = new Coin[n];
            for (int i = 0; i < n; i++) {
                coins[i] = new Coin(in.nextInt(), 0);
            }
            for (int i = 0; i < n; i++) {
                coins[i].y = in.nextInt();
            }
            Arrays.sort(coins);
            solve(coins, m);
        }
    }
}
