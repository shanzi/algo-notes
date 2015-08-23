import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u;
    int v;
    long cost;
    int id;

    public Edge(int u, int v, long cost, int id) {
        this.u = u;
        this.v = v;
        this.cost = cost;
        this.id = id;
    }

    public int compareTo(Edge b) {
        if (this.cost - b.cost > 0) return 1;
        else if (this.cost - b.cost < 0) return -1;
        else return 0;
    }
}

public class GCampus {
    private static HashSet<Integer> dijkstra(int N, int s, List<List<Edge>> graph) {
        long[] dist = new long[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        Edge[] prev = new Edge[N];
    
        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
        que.offer(new Edge(s, s, 0, -1));
    
        while (!que.isEmpty()) {
            Edge p = que.poll();
            if (dist[p.u] < p.cost) continue;
    
            for (Edge e : graph.get(p.u)) {
                if (dist[e.v] > (p.cost + e.cost)) {
                    dist[e.v] = p.cost + e.cost;
                    prev[e.v] = e;
                    que.offer(new Edge(e.v, s, dist[e.v], -1));
                }
            }
        }
        HashSet<Integer> set = new HashSet<Integer>(N);
        for (int i = 0; i < N; i++) {
            if (i == s) continue;
            Edge pv = prev[i];
            while (pv != null) {
                set.add(pv.id);
                pv = prev[pv.u];
            }
        }
        return set;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), M = in.nextInt();
            int[][] cost = new int[N][N];
            ArrayList<List<Edge>> edges = new ArrayList<List<Edge>>(N);
            for (int m = 0; m < M; m++) {
                edges.add(new ArrayList<Edge>(N));
            }
            boolean[] edgesfree = new boolean[M];
            for (int m = 0; m < M; m++) {
                int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
                if (cost[Math.min(a, b)][Math.max(a, b)] == c) continue;
                cost[Math.min(a, b)][Math.max(a, b)] = c;
                edges.get(a).add(new Edge(a, b, c, m));
                edges.get(b).add(new Edge(b, a, c, m));
                edgesfree[m] = true;
            }
            for (int i = 0; i < N; i++) {
                for (int id : dijkstra(N, i, edges)) {
                    edgesfree[id] = false;
                }
            }
            System.out.printf("Case #%d:\n", t + 1);
            for (int i = 0; i < M; i++) {
                if (edgesfree[i]) System.out.println(i);
            }
        }
    }
}
