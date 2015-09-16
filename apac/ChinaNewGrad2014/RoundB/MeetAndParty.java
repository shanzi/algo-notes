import java.io.*;
import java.util.*;

public class MeetAndParty {
    private static long xSum(long[][] rects, long x) {
        long sum = 0;
        for (int i = 0; i < rects.length; i++) {
            long l = rects[i][0], r = rects[i][2], b = rects[i][1], t = rects[i][3];
            if (x < l) {
                sum += (l - x + r - x) * (r - l + 1) / 2 * (t - b + 1);
            } else if (x > r) {
                sum += (x - l + x - r) * (r - l + 1) / 2 * (t - b + 1);
            } else {
                sum += ((x - l) * (x - l + 1) + (r - x) * (r - x + 1)) / 2 * (t - b + 1);
            }
        }
        return sum;
    }
    private static long ySum(long[][] rects, long x) {
        long sum = 0;
        for (int i = 0; i < rects.length; i++) {
            long b = rects[i][0], t = rects[i][2], l = rects[i][1], r = rects[i][3];
            if (x < l) {
                sum += (l - x + r - x) * (r - l + 1) / 2 * (t - b + 1);
            } else if (x > r) {
                sum += (x - l + x - r) * (r - l + 1) / 2 * (t - b + 1);
            } else {
                sum += ((x - l) * (x - l + 1) + (r - x) * (r - x + 1)) / 2 * (t - b + 1);
            }
        }
        return sum;
    }
    private static void solve(long[][] rects) {
        long min = Long.MAX_VALUE;
        int mx = 0, my = 0;
        for (int i = 0; i < rects.length; i++) {
            long l = rects[i][0], r = rects[i][2], b = rects[i][1], t = rects[i][3];
            long minx = Long.MAX_VALUE;
            long miny = Long.MAX_VALUE;
            int x = 0, y = 0;
            for (long j = l; j <= r; j++) {
                long m = xSum(rects, j);
                if (m < minx) {
                    minx = m;
                    x = (int)j;
                }
            }
            for (long j = b; j <= t; j++) {
                long m = ySum(rects, j);
                if (m < miny) {
                    miny = m;
                    y = (int)j;
                }
            }
            if (min > minx + miny) {
                min = minx + miny;
                mx = x;
                my = y;
            } else if (min == minx + miny) {
                if (x < mx || (x == mx && y < my)) {
                    mx = x;
                    my = y;
                }
            }
        }
        System.out.printf("%d %d %d\n", mx, my, min);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int B = in.nextInt();
            long[][] rects = new long[B][4];
            for (int i = 0; i < B; i++) {
                for (int j = 0; j < 4; j++) {
                    rects[i][j] = in.nextLong();
                }
            }
            System.out.printf("Case #%d: ", t + 1);
            solve(rects);
        }
    }
}
