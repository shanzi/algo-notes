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

public class SpaceDefence {
    private static int[] dist = new int[100000];
    private static int dijkstra(int s, int end, List<List<Edge>> graph) {
        Arrays.fill(dist, Integer.MAX_VALUE);
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
    
        return dist[end];
    }
    private static int getColorID(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            res *= 40;
            if (c >= '0' && c <= '9') res += c - '0';
            else res += c - 'a' + 10;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            int W = N + 1600;
            ArrayList<List<Edge>> graph = new ArrayList<List<Edge>>(W);
            for (int i = 0; i < W; i++) {
                graph.add(new ArrayList<Edge>());
            }
            for (int i = 0; i < N; i++) {
                String color = in.next();
                int a = i, b = N + getColorID(color);
                graph.get(a).add(new Edge(a, b, 0));
                graph.get(b).add(new Edge(b, a, 0));
            }
            int M = in.nextInt();
            for (int i = 0; i < M; i++) {
                int a = in.nextInt() - 1, b = in.nextInt() - 1, c = in.nextInt();
                graph.get(a).add(new Edge(a, b, c));
            }
            int S = in.nextInt();
            System.out.printf("Case #%d:\n", t + 1);
            for (int i = 0; i < S; i++) {
                int a = in.nextInt() - 1, b = in.nextInt() - 1;
                int res = dijkstra(a, b, graph);
                if (res == Integer.MAX_VALUE) System.out.println(-1);
                else System.out.println(res);
            }
        }
    }
}
