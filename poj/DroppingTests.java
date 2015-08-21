import java.io.*;
import java.util.*;

public class DroppingTests {
    private static boolean valid(double[][] tests, double m, int k) {
        final double r = m;
        Arrays.sort(tests, new Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                double res = (a[0] - a[1] * r) - (b[0] - b[1] * r);
                if (res > 0) return 1;
                else if (res < 0) return -1;
                else return 0;
            }
        });
        double sum = 0;
        for (int i = tests.length - 1; i >= k; i--) {
            sum += (tests[i][0] - tests[i][1] * r);
        }
        return sum >= 0;
    }
    private static void solve(double[][] tests, int k) {
        double l = 0, u = 100.0;
        while ((u - l) > 0.0009) {
            double m = (l + u) / 2;
            if (valid(tests, m, k)) l = m;
            else u = m;
        }
        System.out.println((int)Math.round(u));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(), K = in.nextInt();
            if (N == 0 && K == 0) return;
            double[][] tests = new double[N][2];
            for (int i = 0; i < N; i++) {
                tests[i][0] = in.nextDouble() * 100.0;
            }
            for (int i = 0; i < N; i++) {
                tests[i][1] = in.nextDouble();
            }
            solve(tests, K);
        }
    }
}
