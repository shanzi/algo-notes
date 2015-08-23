import java.io.*;
import java.util.*;

public class GCube {
    public static double root(double num, double root) {
        return Math.pow(Math.E, Math.log(num)/root);
    } 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), M = in.nextInt();
            double[] dimension = new double[N];
            for (int i = 0; i < N; i++) {
                dimension[i] = in.nextDouble();
            }
            System.out.printf("Case #%d:\n", t + 1);
            for (int j = 0; j < M; j++) {
                int l = in.nextInt(), r = in.nextInt();
                double v = 1.0;
                for (int i = l; i <= r; i++) {
                    v *= root(dimension[i], r - l + 1);
                }
                System.out.printf("%.9f\n", v);
            }
        }
    }
}
