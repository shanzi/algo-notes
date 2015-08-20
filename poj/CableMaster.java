import java.io.*;
import java.util.*;

public class CableMaster {
    private static int cutCount(double[] lengths, double l) {
        int count = 0;
        for (double length : lengths) {
            count += (int)Math.floor(length / l);
        }
        return count;
    }
    private static void solve(double[] lengths, int K) {
        double l = 0, u = 1000000;
        while ((u - l) > 0.004) {
            double m = (l + u) / 2;
            if (cutCount(lengths, m) < K) {
                u = m;
            } else {
                l = m;
            }
        }
        System.out.printf("%.2f\n", Math.floor(u * 100.0) / 100.0);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), K = in.nextInt();
        double[] l = new double[N];
        for (int i = 0; i < N; i++) {
            l[i] = in.nextDouble();
        }
        solve(l, K);
    }
}
