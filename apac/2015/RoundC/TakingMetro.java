import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u;
    int v;
    long cost;

    public Edge(int u, int v, long cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }

    public int compareTo(Edge b) {
        if (this.cost > b.cost) return 1;
        else if (this.cost < b.cost) return -1;
        else return 0;
    }
}

public class TakingMetro {
    static private int L = 1000;
    private static long[] dist;
    private static long dijkstra(int s, int end, List<List<Edge>> graph) {
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[s] = 0;
    
        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
        que.offer(new Edge(s, s, 0));
    
        while (!que.isEmpty()) {
            Edge p = que.poll();
            if (dist[p.u] < p.cost) continue;
    
            for (Edge e : graph.get(p.u)) {
                if (dist[e.v] > p.cost + e.cost) {
                    dist[e.v] = p.cost + e.cost;
                    que.offer(new Edge(e.v, s, dist[e.v]));
                }
            }
        }
        return (dist[end] == Long.MAX_VALUE) ? -1 : dist[end];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            dist = new long[L * N * 2];
            ArrayList<List<Edge>> graph = new ArrayList<List<Edge>>(L * N * 2);
            for (int i = 0; i < L * N * 2; i++) {
                graph.add(new ArrayList<Edge>());
            }
            for (int i = 0; i < N; i++) {
                int SN = in.nextInt();
                int W = in.nextInt();
                for (int j = 0; j < SN; j++) {
                    int s = i * (2 * L) + L + j;
                    int e = i * (2 * L) + j;
                    graph.get(s).add(new Edge(s, e, W));
                    graph.get(e).add(new Edge(e, s, 0));
                }
                for (int j = 0; j < SN - 1; j++) {
                    int s = i * (2 * L) + j;
                    int e = i * (2 * L) + j + 1;
                    int c = in.nextInt();
                    graph.get(s).add(new Edge(s, e, c));
                    graph.get(e).add(new Edge(e, s, c));
                }
            }
            int M = in.nextInt();
            for (int i = 0; i < M; i++) {
                int m1 = in.nextInt() - 1;
                int s1 = in.nextInt() - 1;
                int m2 = in.nextInt() - 1;
                int s2 = in.nextInt() - 1;
                int c = in.nextInt();

                int s = m1 * (2 * L) + L + s1;
                int e = m2 * (2 * L) + L + s2;
                graph.get(s).add(new Edge(s, e, c));
                graph.get(e).add(new Edge(e, s, c));
            }

            System.out.printf("Case #%d:\n", t + 1);

            int Q = in.nextInt();
            for (int i = 0; i < Q; i++) {
                int x1 = in.nextInt() - 1, y1 = in.nextInt() - 1;
                int x2 = in.nextInt() - 1, y2 = in.nextInt() - 1;
                int s = x1 * (2 * L) + L + y1;
                int e = x2 * (2 * L) + L + y2;

                System.out.println(dijkstra(s, e, graph));
            }
        }
    }
}
