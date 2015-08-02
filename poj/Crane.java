import java.io.*;
import java.util.*;

public class Crane {
    private static int l = 1;
    private static double[] deg;
    private static double[] cur;
    private static double[] vx;
    private static double[] vy;

    private static void init(int[] L) {
        l = 1;
        while (l < L.length) l <<= 1;

        vx = new double[l * 2 - 1];
        vy = new double[l * 2 - 1];
        deg = new double[l * 2 - 1];
        cur = new double[l * 2 - 1];

        for (int i = 0; i < L.length; i++) {
            vy[i + l - 1] = L[i];
        }

        for (int i = l - 2; i >= 0; i--) {
            vy[i] = vy[i * 2 + 1] + vy[i * 2 + 2];
        }

        Arrays.fill(cur, Math.PI);
    }

    private static void operate(int s, double ang, int v, int left, int right) {
        if (s > left && s <= right) {
            int chl = v * 2 + 1;
            int chr = v * 2 + 2;
            int m = (left + right) / 2 + 1;
            operate(s, ang, chl, left, m - 1);
            operate(s, ang, chr, m, right);

            if (s <= m) deg[v] += ang;

            double sin = Math.sin(deg[v]);
            double cos = Math.cos(deg[v]);
            vx[v] = vx[chl] + (cos * vx[chr] - sin * vy[chr]);
            vy[v] = vy[chl] + (sin * vx[chr] + cos * vy[chr]);
        }
    }

    private static void solve(int[] L, int[]opn, double[] opdeg) {
        init(L);
        for (int i = 0; i < opn.length; i++) {
            int s = opn[i];
            operate(s, opdeg[i] - cur[s], 0, 0, L.length - 1);
            cur[s] = opdeg[i];

            System.out.printf("%.2f %.2f\n", vx[0], vy[0]);
        }
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        boolean flag = false;
        while (stdin.hasNextInt()) {
            if (flag) {
                System.out.println();
            } else {
                flag = true;
            }
            int ln = stdin.nextInt(), cn = stdin.nextInt();
            int[] L = new int[ln];
            int[] opn = new int[cn];
            double[] opdeg = new double[cn];

            for (int i = 0; i < ln; i++) {
                L[i] = stdin.nextInt();
            }

            for (int i = 0; i < cn; i++) {
                opn[i] = stdin.nextInt();
                opdeg[i] = stdin.nextInt() / 180.0 * Math.PI;
            }

            solve(L, opn, opdeg);
        }
    }
}
