import java.io.*;
import java.util.*;

public class gBalloon {
    private static int INF = 20000;
    private static int N, M, Q;
    private static int[] velocities = new int[1010];
    private static int[] P = new int[110];
    private static int[] H = new int[110];
    private static int cost(int time) {
        int cost = 0;
        for (int i = 0; i < N; i++) {
            int p = P[i];
            int h = H[i];
            int minCost = INF;
            if (p == 0) {
                minCost = 0;
            } else {
                for (int j = 0; j < M; j++) {
                    if ((velocities[j] ^ p) < 0 && velocities[j] != 0) {
                        int timeCost = Math.abs(p) / Math.abs(velocities[j]);
                        if (Math.abs(p) % Math.abs(velocities[j]) > 0) timeCost++;

                        if (timeCost <= time) minCost = Math.min(minCost, Math.abs(h - j));
                    }
                }
            }
            cost += minCost;
        }
        return cost;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            N = in.nextInt();
            M = in.nextInt();
            Q = in.nextInt();
            for (int i = 0; i < M; i++) {
                velocities[i] = in.nextInt();
            }

            for (int i = 0; i < N; i++) {
                P[i] = in.nextInt();
                H[i] = in.nextInt();
            }
            int l = 0, r = INF;
            while (l <= r) {
                int m = (l + r) / 2;
                if (cost(m) <= Q) r = m - 1;
                else l = m + 1;
            }

            System.out.printf("Case #%d: ", t + 1);
            if (l >= INF) System.out.println("IMPOSSIBLE");
            else System.out.println(l);
        }
    }
}
