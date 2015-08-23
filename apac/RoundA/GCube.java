import java.io.*;
import java.util.*;

public class GCube {
    public static double root(double num, double root) {
        return Math.pow(Math.E, Math.log(num)/root);
    } 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt(), M = in.nextInt();
            double[] volume = new double[N + 1];
            volume[0] = 1;
            for (int j = 1; j <= N; j++) {
                volume[j] = volume[j - 1] * in.nextDouble();
            }
            System.out.printf("Case #%d:\n", i + 1);
            for (int j = 0; j < M; j++) {
                int l = in.nextInt(), r = in.nextInt();
                System.out.printf("%.9f\n", root(volume[r + 1], r - l + 1)/root(volume[l], r - l + 1));
            }
        }
    }
}
