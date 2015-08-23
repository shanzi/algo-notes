import java.io.*;
import java.util.*;

public class Garland {

    private static boolean greaterThanZero(double[] height) {
        for (int i = 2; i < height.length; i++) {
            height[i] = 2 * height[i - 1] + 2 - height[i - 2];
            if (height[i] < 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        double[] height = new double[N];
        height[0] = in.nextDouble();

        double l = -1, u = 1016;
        double res = 0;
        for (int i = 0; i < 100; i++) {
            double mid = (u + l) / 2;
            height[1] = mid;
            if (greaterThanZero(height)) {
                u = mid;
                res = height[N - 1];
            } else l = mid;
        }
        System.out.printf("%.2f\n", res);
    }
}
