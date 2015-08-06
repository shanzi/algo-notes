import java.io.*;
import java.util.*;

public class ArrangeTheBulls {
    private static void solve(int N, int M, int[] bulls) {
        int[] dplast = new int[1 << M];
        int[] dpcur = new int[1 << M];
        int W = (1 << M) - 1;

        dplast[W] = 1;
        for (int i = 1; i <= N; i++) {
            int bull = bulls[i - 1];
            for (int j = W; j > 0; j--) {
                if (dplast[j] == 0) continue;

                int mask = j & bull;
                while (mask > 0) {
                    dpcur[j & ~(mask & -mask)] += dplast[j];
                    mask &= mask - 1;
                }
            }

            int[] tmp = dplast;
            dplast = dpcur;
            dpcur = tmp;
            Arrays.fill(dpcur, 0);
        }
        int res = 0;
        for (int i = 0; i <= W; i++) {
            res += dplast[i];
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(), M = in.nextInt();
            int[] bulls = new int[N];
            for (int i = 0; i < N; i++) {
                int P = in.nextInt();
                for (int j = 0; j < P; j++) {
                    int b = in.nextInt() - 1;
                    bulls[i] |= (1 << b);
                }
            }

            solve(N, M, bulls);
        }
    }
}
