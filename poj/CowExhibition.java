import java.io.*;
import java.util.*;

class Cow implements Comparable<Cow>{
    int s;
    int f;
    public Cow(int s, int f) {
        this.s = s;
        this.f = f;
    }
    public int compareTo(Cow that) {
        Cow a = this;
        Cow b = that;
        if (a.s < b.s) return 1;
        else if (a.s > b.s) return -1;
        else if (a.f < b.f) return 1;
        else if (a.f > b.f) return -1;
        else return 0;
    }
}

public class CowExhibition {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Cow[] cows = new Cow[N];
        for (int i = 0; i < N; i++) {
            cows[i] = new Cow(in.nextInt(), in.nextInt());
        }
        Arrays.sort(cows);
        long[] dp = new long[102000];
        Arrays.fill(dp, -102000);
        dp[0] = 0;
        int maxs = 0;
        for (int i = 0; i < N; i++) {
            Cow c = cows[i];
            if (c.s >= 0) {
                for (int j = maxs + c.s; j >= c.s; j--) {
                    dp[j] = Math.max(dp[j], dp[j - c.s] + c.f);
                }
            } else {
                for (int j = 0; j <= maxs + c.s; j++) {
                    dp[j] = Math.max(dp[j], dp[j - c.s] + c.f);
                }
            }
            maxs = Math.max(maxs, maxs + c.s);
        }
        long max = 0;
        for (int i = 0; i <= maxs; i++) {
            if (dp[i] >= 0) max = Math.max(max, i + dp[i]);
        }
        System.out.println(max);
    }
}
