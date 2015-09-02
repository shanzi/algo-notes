import java.io.*;
import java.util.*;

public class BridgingSingals {
    static private int[] array = new int[40010];
    static private int[] dp = new int[40010];
    static private int[] count = new int[40010];

    private static int upperbound(int[] array, int value, int len) {
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] <= value) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
    private static void solve(int p) {
        for (int i = 0; i <= p; i++) {
            count[i] = 50000;
        }
        count[0] = Integer.MIN_VALUE;
        int max = 1;
        for (int i = 0; i < p; i++) {
            int c = upperbound(count, array[i], p);
            dp[i] = Math.max(1, c + 1);
            count[dp[i]] = Math.min(count[dp[i]], array[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int p = in.nextInt();
            for (int i = 0; i < p; i++) {
                array[i] = in.nextInt();
            }
            solve(p);
        }
    }
}
