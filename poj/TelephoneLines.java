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

public class TelephoneLines {
    private static int dijkstra(int N, int mid, List<List<Edge>> graph) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
    
        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
        que.offer(new Edge(0, 0, 0));
    
        while (!que.isEmpty()) {
            Edge p = que.poll();
            if (dist[p.u] < p.cost) continue;
    
            for (Edge e : graph.get(p.u)) {
                int newd = p.cost + ((e.cost > mid) ? 1 : 0);
                if (dist[e.v] > newd) {
                    dist[e.v] = newd;
                    que.offer(new Edge(e.v, 0, dist[e.v]));
                }
            }
        }
    
        return dist[N - 1];
    }
    private static void solve(int N, int K, List<List<Edge>> graph) {
        int l = 0, u = 1000005;
        while (l <= u) {
            int mid = (l + u) / 2;
            if (dijkstra(N, mid, graph) <= K) u = mid - 1;
            else l = mid + 1;
        }
        if (l > 1000000)
            System.out.println(-1);
        else
            System.out.println(l);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        str = in.readLine().split(" ");
        int N = Integer.parseInt(str[0]), P = Integer.parseInt(str[1]), K = Integer.parseInt(str[2]);
        ArrayList<List<Edge>> edges = new ArrayList<List<Edge>>(N);
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<Edge>());
        }
        for (int i = 0; i < P; i++) {
            str = in.readLine().split(" ");
            int a = Integer.parseInt(str[0]) - 1, b = Integer.parseInt(str[1]) - 1, c = Integer.parseInt(str[2]);
            edges.get(a).add(new Edge(a, b, c));
            edges.get(b).add(new Edge(b, a, c));
        }
        solve(N, K, edges);
    }
}
