import java.io.*;
import java.util.*;

public class CapitainHammper {
    private static double solve(int V, int D) {
        double C = (D * 9.8) / (double)(V * V);
        double dtheta = Math.asin(Math.min(1.0, C));
        return (dtheta / (2 * Math.PI) * 180);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int T = in.nextInt();
            for (int t = 0; t < T; t++) {
                int V = in.nextInt(), D = in.nextInt();

                System.out.printf("Case #%d: %.7f\n", t + 1, solve(V, D));
            }
        }
    }
}
