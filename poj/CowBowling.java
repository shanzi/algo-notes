import java.io.*;
import java.util.*;

public class CowBowling {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] dp = new int[N];
        int[] last = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                int c = in.nextInt();
                if (j > 0) dp[j] = Math.max(last[j - 1], last[j]) + c;
                else dp[j] = last[j] + c;
            }
            int[] tmp = dp;
            dp = last;
            last = tmp;
        }

        int max = 0;
        for (int m : last) {
            max = Math.max(max, m);
        }
        System.out.println(max);
    }
}
