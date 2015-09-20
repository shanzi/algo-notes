import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int cost;

    public Edge(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }

    public int compareTo(Edge b) {
        return this.cost - b.cost;
    }
}

public class Travel {
    private static int[][] dist = new int[24][12000];
    private static void dijkstra(int s, List<List<Edge>> graph) {
        Arrays.fill(dist[s], Integer.MAX_VALUE);
        dist[s][s] = 0;
    
        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
        que.offer(new Edge(s, s, 0));
    
        while (!que.isEmpty()) {
            Edge p = que.poll();
            if (dist[s][p.u] < p.cost) continue;
    
            for (Edge e : graph.get(p.u)) {
                if (dist[s][e.v] > p.cost + e.cost) {
                    dist[s][e.v] = p.cost + e.cost;
                    que.offer(new Edge(e.v, s, dist[s][e.v]));
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), M = in.nextInt(), K = in.nextInt();
            ArrayList<List<Edge>> graph = new ArrayList<List<Edge>>(N * 24);
            for (int i = 0; i < N * 24; i++) {
                graph.add(new ArrayList<Edge>());
            }
            for (int i = 0; i < M; i++) {
                int x = in.nextInt() - 1, y = in.nextInt() - 1;
                for (int j = 0; j < 24; j++) {
                    int cost = in.nextInt();
                    int a = x * 24 + j;
                    int b = y * 24 + (j + cost) % 24;
                    int c = y * 24 + j;
                    int d = x * 24 + (j + cost) % 24;
                    graph.get(a).add(new Edge(a, b, cost));
                    graph.get(c).add(new Edge(c, d, cost));
                }
            }
            System.out.printf("Case #%d:", t + 1);
            for (int i = 0; i < 24; i++) {
                dijkstra(i, graph);
            }
            for (int i = 0; i < K; i++) {
                int D = in.nextInt() - 1, S = in.nextInt();
                int a = S;
                int b = D * 24;
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < 24; j++) {
                    min = Math.min(min, dist[a][b + j]);
                }
                System.out.printf(" %d", (min == Integer.MAX_VALUE) ? -1 : min);
            }
            System.out.println();
        }
    }
}
