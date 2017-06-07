import java.io.*;
import java.util.*;

public class TheQuickestWayUp {
    static int[][]graph = new int[101][7];
    static int[]ladders= new int[101];
    static int[]snakes= new int[101];

    private static int spfa() {
        int[] dist = new int[101];
        Arrays.fill(dist, 105);
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(1);
        dist[1] = 0;
        while (!que.isEmpty()) {
            int u = que.poll();

            for (int i = 1; i <= 6; i++) {
                int v = graph[u][i];
                if (v == 0) continue;
                if (dist[v] > dist[u] + 1) {
                    dist[v] = dist[u] + 1;
                    que.add(v);
                }
            }
        }
        return dist[100] > 200 ? -1 : dist[100];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            for (int i = 1; i <= 100; i++) {
                ladders[i] = 0;
                snakes[i] = 0;
                Arrays.fill(graph[i], 0);
            }

            int L = in.nextInt();

            for (int l = 0; l < L; l++) {
                int a = in.nextInt(), b = in.nextInt();
                ladders[a] = b;
            }

            int S = in.nextInt();

            for (int s = 0; s < S; s++) {
                int a = in.nextInt(), b = in.nextInt();
                snakes[a] = b;
            }

            for (int i = 1; i <= 100; i++) {
                if (ladders[i] > 0 || snakes[i] > 0) continue;
                for (int j = 1; j <= 6 && (i + j <= 100); j++) {
                    if (ladders[i + j] > 0) {
                        graph[i][j]  = ladders[i + j];
                    } else if (snakes[i + j] > 0) {
                        graph[i][j]  = snakes[i + j];
                    } else {
                        graph[i][j] = i + j;
                    }
                }
            }
            System.out.println(spfa());
        }
    }
}
