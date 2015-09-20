import java.io.*;
import java.util.*;

public class gWheels {
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
    private static void solve(long p, long q, long[] pwheel, long[] ewheel, long[] twheel) {
        Arrays.sort(pwheel);
        Arrays.sort(ewheel);
        Arrays.sort(twheel);
        long pqg = gcd(p, q);
        p /= pqg;
        q /= pqg;
        long pmax = pwheel[pwheel.length - 1];
        long tmax = twheel[twheel.length - 1];
        for (int i = 0; i < ewheel.length; i++) {
            for (int j = 0; j < ewheel.length; j++) {
                if (i == j) continue;
                long a = ewheel[i] * p;
                long b = ewheel[j] * q;
                long abg = gcd(a, b);
                a /= abg;
                b /= abg;
                for (int k = 1; k * a <= pmax && k * b <= tmax; k++) {
                    if (Arrays.binarySearch(pwheel, k * a) >= 0 && Arrays.binarySearch(twheel, k * b) >= 0) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }
        System.out.println("No");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int TEST = in.nextInt();
        for (int t = 0; t < TEST; t++) {
            int P = in.nextInt(), E = in.nextInt(), T = in.nextInt();
            long[] pwheel = new long[P];
            long[] ewheel = new long[E];
            long[] twheel = new long[T];
            for (int i = 0; i < P; i++) {
                pwheel[i] = in.nextLong();
            }
            for (int i = 0; i < E; i++) {
                ewheel[i] = in.nextLong();
            }
            for (int i = 0; i < T; i++) {
                twheel[i] = in.nextLong();
            }
            System.out.printf("Case #%d:\n", t + 1);
            int M = in.nextInt();
            for (int i = 0; i < M; i++) {
                long p = in.nextLong(), q = in.nextLong();
                solve(p, q, pwheel, ewheel, twheel);
            }
        }
    }
}
