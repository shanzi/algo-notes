import java.io.*;
import java.util.*;

public class JanesFlowerShop {
    static double sumAll(double rate, double cost, double[] income) {
        double res = 0;
        double prod = 1.0;

        for (int i = 0; i < income.length; i++) {
            res += income[income.length - i - 1] * prod;
            prod *= rate;
        }

        res -= cost * prod;
        return res;
    }
    static double solve(double cost, double[] income) {
        double rx = -1.0, ry = 1.0, rm = 0;
        for (int i = 0; i < 10000; i++) {
            rm = (rx + ry) / 2;
            double sum = sumAll(1.0 + rm, cost, income);
            if (sum > 0) rx = rm;
            else ry = rm;
        }
        return rm;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            double cost = in.nextDouble();
            double[] income = new double[N];
            for (int i = 0; i < N; i++) {
                income[i] = in.nextDouble();
            }
            System.out.printf("Case #%d: %.12f\n", t + 1, solve(cost, income));
        }
    }
}
