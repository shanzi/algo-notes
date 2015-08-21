import java.io.*;
import java.util.*;

public class Drying {
    private static boolean canDry(int[] things, int t, int k) {
        int tl = t;

        if (k == 1) return (things[things.length - 1] <= t);

        for (int i = things.length - 1; i >= 0; i--) {
            int a = things[i];
            if (a <= t) return true;
            int d = (a - t) / (k - 1);
            if ((a - t) % (k - 1) != 0) d++;
            if (d <= tl) tl -= d;
            else return false;
        }
        return true;
    }
    private static void solve(int[] things, int K) {
        int l = 0, u = 1000000000;
        while (l <= u) {
            int t = (l + u) / 2;
            if (canDry(things, t, K)) u = t - 1;
            else l = t + 1;
        }
        System.out.println(l);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            int[] things = new int[N];
            for (int i = 0; i < N; i++) things[i] = in.nextInt();
            Arrays.sort(things);
            int K = in.nextInt();
            solve(things, K);
        }
    }
}
