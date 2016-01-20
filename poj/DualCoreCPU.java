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
    private int[] level;
    private int[] iter;
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
        int flow = 0;
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

public class DualCoreCPU {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        str = in.readLine().split(" ");
        int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]);
        NetworkFlow nf = new NetworkFlow(N + 2);
        for (int i = 0; i < N; i++) {
            str = in.readLine().split(" ");
            int A = Integer.parseInt(str[0]), B = Integer.parseInt(str[1]);
            nf.addEdge(0, i + 1, B);
            nf.addEdge(i + 1, N + 1, A);
        }
        for (int i = 0; i < M; i++) {
            str = in.readLine().split(" ");
            int a = Integer.parseInt(str[0]), b = Integer.parseInt(str[1]), w = Integer.parseInt(str[2]);
            nf.addEdge(a, b, w);
            nf.addEdge(b, a, w);
        }
        System.out.println(nf.maxFlow(0, N + 1));
    }
}
