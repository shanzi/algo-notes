import java.io.*;
import java.util.*;

class Edge {
    int u;
    int v;
    int cost;

    public Edge(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}

public class Wormholes {
    private static void solve(int N, List<Edge> edges) {
        int[] dist = new int[N];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        boolean possible = false;

        for (int i = 0; i < N; i++) {
            for (Edge e : edges) {
                if (dist[e.u] < Integer.MAX_VALUE && dist[e.v] > dist[e.u] + e.cost) {
                    dist[e.v] = dist[e.u] + e.cost;
                    if (i == N - 1) {
                        possible = true;
                        break;
                    }
                }

            }
        }

        if (possible) System.out.println("YES");
        else System.out.println("NO");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int F = in.nextInt();
            for (int f = 0; f < F; f++) {
                int N = in.nextInt(), M = in.nextInt(), W = in.nextInt();

                ArrayList<Edge> edges = new ArrayList<Edge>(M * 2 + W);

                for (int i = 0; i < M; i++) {
                    int u = in.nextInt() - 1, v = in.nextInt() - 1, cost = in.nextInt();
                    edges.add(new Edge(u, v, cost));
                    edges.add(new Edge(v, u, cost));
                }

                for (int i = 0; i < W; i++) {
                    int u = in.nextInt() - 1, v = in.nextInt() - 1, cost = in.nextInt();
                    edges.add(new Edge(u, v, -cost));
                }

                solve(N, edges);
            }
        }
    }
}
