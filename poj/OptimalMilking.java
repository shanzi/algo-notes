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

class NetworkFlow {
    private static class NFEdge {
        int v;
        int rev;
        int cap;
        public NFEdge(int v, int rev, int cap) { 
            this.v = v;
            this.rev = rev;
            this.cap = cap;
        }
    }
    private int[] level;
    private int[] iter;
    private int flow = 0;
    private List<List<NFEdge>> graph;
    public NetworkFlow(int N) { 
        level = new int[N];
        iter = new int[N];
        graph = new ArrayList<List<NFEdge>>(N);
        for (int i = 0; i < N; i++) graph.add(new ArrayList<NFEdge>());
    }
    private void bfs(int s) {
        Arrays.fill(level, -1);
        Queue<Integer> que = new LinkedList<Integer>();
        level[s] = 0;
        que.add(s);
        while(!que.isEmpty()) {
            int v = que.poll();
            for (NFEdge e: graph.get(v)) {
                if (e.cap > 0 && level[e.v] < 0) {
                    level[e.v] = level[v] + 1;
                    que.add(e.v);
                }
            }
        }
    }
    private int dfs(int u, int t, int cap) {
        if (u == t) return cap;
        List<NFEdge> neighbors = graph.get(u);
        for (; iter[u] < neighbors.size(); iter[u]++) {
            NFEdge e = neighbors.get(iter[u]);
            if (e.cap > 0 && level[u] < level[e.v]) {
                int mcap = dfs(e.v, t, Math.min(cap, e.cap));
                if (mcap > 0) {
                    e.cap -= mcap;
                    graph.get(e.v).get(e.rev).cap += mcap;
                    return mcap;
                }
            }
        }
        return 0;
    }
    public int maxFlow(int start, int target) {
        while (true) {
            bfs(start);
            if (level[target] < 0) return flow;
            Arrays.fill(iter, 0);
            int mcap;
            while ((mcap = dfs(start, target, Integer.MAX_VALUE)) > 0) {
                flow += mcap;
            } 
        }
    }
    public void addEdge(int u, int v, int cap) {
        graph.get(u).add(new NFEdge(v, graph.get(v).size(), cap));
        graph.get(v).add(new NFEdge(u, graph.get(u).size() - 1, 0));
    }
}

public class OptimalMilking {
    static private int[][] dist;
    static private int INF = 100000000;
    private static void dijkstra(int s, List<List<Edge>> graph) {
        Arrays.fill(dist[s], INF);
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
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        str = in.readLine().split(" ");
        int K = Integer.parseInt(str[0]), C = Integer.parseInt(str[1]), M = Integer.parseInt(str[2]);
        dist = new int[K][K + C];
        List<List<Edge>> graph = new ArrayList<List<Edge>>(K + C);
        ArrayList<Integer> numberbuff = new ArrayList<Integer>(K + C);
        for (int i = 0; i < K + C; i++) {
            ArrayList<Edge> nb = new ArrayList<Edge>();
            numberbuff.clear();
            while(numberbuff.size() < K + C) {
                str = in.readLine().split(" ");
                for (String n: str) numberbuff.add(Integer.parseInt(n));
            }
            for (int j = 0; j < K + C; j++) {
                int cost = numberbuff.get(j);
                if (cost > 0) nb.add(new Edge(i, j, cost));
            }
            graph.add(nb);
        }
        Edge[] edges = new Edge[K * C];
        for (int i = 0; i < K; i++) {
            dijkstra(i, graph);
            for (int j = 0; j < C; j++) {
                edges[i * C + j] = new Edge(i, j, dist[i][K + j]);
            }
        }
        Arrays.sort(edges);
        NetworkFlow nf = new NetworkFlow(K + C + 2);
        for (int i = 0; i < K; i++) nf.addEdge(K + C, i, M);
        for (int i = 0; i < C; i++) nf.addEdge(K + i, K + C + 1, 1);
        for (int i = 0; i < edges.length;) {
            int cost = edges[i].cost;
            while (i < edges.length && edges[i].cost <= cost) {
                Edge e = edges[i];
                nf.addEdge(e.u, K + e.v, 1);
                i++;
            }
            if (nf.maxFlow(K + C, K + C + 1) >= C) {
                System.out.println(cost);
                return;
            }
        }
    }
}
