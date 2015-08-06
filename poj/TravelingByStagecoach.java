import java.io.*;
import java.util.*;

public class TravelingByStagecoach {
    private static void solve(int a, int b, int[] tickets, int[][] cities) {
        double[][] DP = new double[1 << tickets.length][cities.length];

        for (int i = 0; i < DP.length; i++) {
            Arrays.fill(DP[i], (double)Integer.MAX_VALUE);
        }

        DP[0][a] = 0;
        for (int i = 0; i < DP.length - 1; i++) {
            for (int j = 0; j < cities.length; j++) {
                if (DP[i][j] > 1000000.0) continue;

                for (int k = 0; k < tickets.length; k++) {
                    if (((i >> k) & 1) == 1) continue;
                    int mask = i | (1 << k);
                    double horse = tickets[k];

                    for (int l = 0; l < cities.length; l++) {
                        if (cities[j][l] > 100) continue;
                        DP[mask][l] = Math.min(DP[mask][l], DP[i][j] + cities[j][l] / horse);
                    }
                }
            }
        }

        double res = 1000000.0;
        for (int i = 0; i < DP.length; i++) {
            res = Math.min(res, DP[i][b]);
        }

        if (res > 999999.0) System.out.println("Impossible");
        else System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        while (in.hasNextInt()) {
            int n = in.nextInt(), m = in.nextInt(), p = in.nextInt();
            int a = in.nextInt() - 1, b = in.nextInt() - 1;

            if (a < 0 && b < 0) break;

            int[] tickets = new int[n];

            for (int i = 0; i < n; i++) {
                tickets[i] = in.nextInt();
            }

            int[][] cities = new int[m][m];
            for (int i = 0; i < m; i++) {
                Arrays.fill(cities[i], Integer.MAX_VALUE);
            }
            for (int i = 0; i < p; i++) {
                int c1 = in.nextInt() - 1, c2 = in.nextInt() - 1;
                cities[c1][c2] = in.nextInt();
                cities[c2][c1] = cities[c1][c2];
            }

            solve(a, b, tickets, cities);
        }
    }
}
