import java.io.*;
import java.util.*;

public class SixDegreesOfCowvinBacon {
    private static int INF = 10000000;
    private static void solve(int[][] dist) {
        int N = dist.length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int minsum = INF;

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += dist[i][j];
            }

            minsum = Math.min(minsum, sum);
        }

        System.out.println(minsum * 100 / (N - 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str = reader.readLine().split(" ");

        int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]);

        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            str = reader.readLine().split(" ");
            int[] cows = new int[str.length];

            for (int j = 1; j < str.length; j++) {
                cows[j] = Integer.parseInt(str[j]) - 1;
            }

            for (int j = 1; j < cows.length; j++) {
                for (int k = j + 1; k < cows.length; k++) {
                    dist[cows[j]][cows[k]] = 1;
                    dist[cows[k]][cows[j]] = 1;
                }
            }
        }

        solve(dist);
    }
}
