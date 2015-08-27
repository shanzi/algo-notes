import java.io.*;
import java.util.*;

class Island implements Comparable<Island>{
    int x;
    int y;
    public Island(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int compareTo(Island that) {
        Island a = this;
        Island b = that;
        return ((a.x == b.x) ? a.y - b.y : a.x - b.x);
    }
}
public class RadarInstallation {
    private static void solve(Island[] islands, double D) {
        double D2 = D * D;
        int count = 0;
        for (int i = 0; i < islands.length;) {
            Island isl = islands[i];
            if (isl.y > D) {
                System.out.println(-1);
                return;
            }
            double right = isl.x + Math.sqrt(D2 - isl.y * isl.y);
            while ((++i) < islands.length) {
                double x = islands[i].x;
                double y = islands[i].y;
                if (y > D) break;
                double rx = Math.sqrt(D2 - y * y);
                if (x - rx > right) break;
                right = Math.min(right, x + rx);
            }
            count++;
        }
        System.out.println(count);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = 1;
        while (in.hasNextInt()) {
            int N = in.nextInt(), D = in.nextInt();
            if (N == 0 && D == 0) return;
            Island[] islands = new Island[N];
            for (int i = 0; i < N; i++) {
                islands[i] = new Island(in.nextInt(), in.nextInt());
            }
            Arrays.sort(islands);
            System.out.printf("Case %d: ", t++);
            solve(islands, D);
        }
    }
}
