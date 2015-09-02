import java.io.*;
import java.util.*;

class Stick implements Comparable<Stick>{
    int l;
    int w;
    public Stick(int l, int w) {
        this.l = l;
        this.w = w;
    }
    public int compareTo(Stick that) {
        Stick a = this;
        Stick b = that;
        if (a.l < b.l) return -1;
        else if (a.l > b.l) return 1;
        else if (a.w < b.w) return -1;
        else if (a.w > b.w) return 1;
        else return 0;
    }
}

public class WoodenSticks {
    private static void solve(Stick[] sticks) {
        int N = sticks.length;
        int[] dp = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (sticks[j].w > sticks[i].w) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            Stick[] sticks = new Stick[N];
            for (int i = 0; i < N; i++) {
                sticks[i] = new Stick(in.nextInt(), in.nextInt());
            }
            Arrays.sort(sticks);
            solve(sticks);
        }
    }
}
