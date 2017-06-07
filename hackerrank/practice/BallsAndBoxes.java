import java.io.*;
import java.util.*;

class Edge {
    int u;
    int v;
    int cap;
    int cost;
    Edge rev;

    public Edge(int u, int v, int cap, int cost) {
        this.u = u;
        this.v = v;
        this.cap = cap;
        this.cost = cost;
    }
}

class MinimumCostNF {

	int N;
    int[] dist;
    int[] prevv;
    Edge[] preve;
    ArrayList<Edge> edges;

    public MinimumCostNF(int N) { 
		this.N = N;
        dist = new int[N];
        prevv = new int[N];
        preve = new Edge[N];
        edges = new ArrayList<Edge>(N * (N - 1));
    }

    private boolean bellmanford(int s) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
    
        for (int i = 0; i < N; i++) {
            for (Edge e : edges) {
                if (dist[e.u] < Integer.MAX_VALUE && e.cap > 0 && dist[e.v] > dist[e.u] + e.cost) {
                    dist[e.v] = dist[e.u] + e.cost;
                    prevv[e.v] = e.u;
                    preve[e.v] = e;
                    if (i == N - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addEdge(int u, int v, int cap, int cost) {
        Edge a = new Edge(u, v, cap, cost);
        Edge b = new Edge(v, u, 0, -cost);
        a.rev = b;
        b.rev = a;

        edges.add(a);
        edges.add(b);
    }

    public int minCostFlow(int s, int t) {
        int min = 0;
        int res = 0;

        while(true) {
            bellmanford(s);

            if (dist[t] == Integer.MAX_VALUE) break;

            res += dist[t];
            min = Math.min(min, res);

            for (int v = t; v != s; v = prevv[v]) {
                preve[v].cap -= 1;
                preve[v].rev.cap += 1;
            }
        }
        return min;
    }
}

public class BallsAndBoxes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = in.nextInt();
        }

        MinimumCostNF mc = new MinimumCostNF(n + m + 2);   

        for (int i = 0; i < n; i++) {
            int u = i + 2;
            mc.addEdge(0, u, A[i], 0);

            for (int j = 0; j < m; j++) {
                int cost = in.nextInt();
                int v = 2 + n + j;

                mc.addEdge(u, v, 1, -cost);
            }
        }

        for (int j = 0; j < m; j++) {
            int v = 2 + n + j;
            mc.addEdge(v, 1, B[j], 0);
            for (int k = 0; k < 40; k++) {
                mc.addEdge(v, 1, 1, k * 2 + 1);
            }
        }

        System.out.println(-mc.minCostFlow(0, 1));
    }
}

