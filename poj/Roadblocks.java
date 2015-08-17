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

class Distance implements Comparable<Distance> {
    int u;
    int d;

    public Distance(int u, int d) {
        this.u = u;
        this.d = d;
    }

    public int compareTo(Distance another) {
        return this.d - another.d;
    }
}

public class Roadblocks {
    private static void solve(int N, List<List<Edge>> graph) {
        int[] dist = new int[N];
        int[] dist2 = new int[N];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);

        dist[0] = 0;

        PriorityQueue<Distance> que = new PriorityQueue<Distance>();

        que.offer(new Distance(0, 0));

        while (!que.isEmpty()) {
            Distance p = que.poll();
            if (dist2[p.u] < p.d) continue; 

            for (Edge e : graph.get(p.u)) {
                int d2 = p.d + e.cost;

                if (dist[e.v] > d2) {
                    int t = d2;
                    d2 = dist[e.v];
                    dist[e.v] = t;

                    que.offer(new Distance(e.v, dist[e.v]));
                }

                if (dist2[e.v] > d2) {
                    dist2[e.v] = d2;

                    que.offer(new Distance(e.v, dist2[e.v]));
                }
            }
        }

        System.out.println(dist2[N - 1]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(), R = in.nextInt();
            ArrayList<List<Edge>> graph = new ArrayList<List<Edge>>(N);

            for (int i = 0; i < N; i++) graph.add(new ArrayList<Edge>());

            for (int i = 0; i < R; i++) {
                int u = in.nextInt() - 1, v = in.nextInt() - 1, cost = in.nextInt();
                graph.get(u).add(new Edge(u, v, cost));
                graph.get(v).add(new Edge(v, u, cost));
            }

            solve(N, graph);
        }
    }
}
