import java.io.*;
import java.util.*;

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
    private boolean[] visited;
    private List<List<NFEdge>> graph;

    public NetworkFlow(int N) { 
        visited = new boolean[N];
        graph = new ArrayList<List<NFEdge>>(N);
        for (int i = 0; i < N; i++) graph.add(new ArrayList<NFEdge>(N));
    }

    private int dfs(int u, int t, int cap) {
        if (u == t) return cap;
        visited[u] = true;
        for (NFEdge e: graph.get(u)) {
            if (e.cap > 0 && !visited[e.v]) {
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
        int flow = 0;
        while (true) {
            Arrays.fill(visited, false);
            int mcap = dfs(start, target, Integer.MAX_VALUE);
            if (mcap <= 0) break;
            else flow += mcap;
        }
        return flow;
    }

    public void addEdge(int u, int v, int cap) {
        graph.get(u).add(new NFEdge(v, graph.get(v).size(), cap));
        graph.get(v).add(new NFEdge(u, graph.get(u).size() - 1, 0));
    }
}

public class Dining {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), F = in.nextInt(), D = in.nextInt();
        int W = 2 + F + N + N + D;

        NetworkFlow nf = new NetworkFlow(W);

        for (int i = 0; i < N; i++) {
            nf.addEdge(2 + F + i, 2 + F + N + i, 1);
            int f = in.nextInt(), d = in.nextInt();
            for (int j = 0; j < f; j++) {
                int u = 2 + in.nextInt() - 1;
                int v = 2 + F + i;
                nf.addEdge(u, v, 1);
            }
            for (int j = 0; j < d; j++) {
                int u = 2 + F + N + i;
                int v = 2 + F + N + N + in.nextInt() - 1;
                nf.addEdge(u, v, 1);
            }
        }
        for (int i = 0; i < F; i++) {
            nf.addEdge(0, 2 + i, 1);
        }
        for (int i = 0; i < D; i++) {
            nf.addEdge(2 + F + N + N + i, 1, 1);
        }
        System.out.println(nf.maxFlow(0, 1));
    }
}
