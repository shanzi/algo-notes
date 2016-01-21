import java.io.*;
import java.util.*;

class BipartiteMatching {
    int[] match;
    boolean[] used;
    List<List<Integer>> graph;
    public BipartiteMatching(int N) { 
        match = new int[N];
        used = new boolean[N];
        graph = new ArrayList<List<Integer>>(N);
        for (int i = 0; i < N; i++) graph.add(new ArrayList<Integer>());
    }
    public void addEdge(int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
    private boolean dfs(int u) {
        used[u] = true;
        for (int v: graph.get(u)) {
            int w = match[v];
            if (w < 0 || !used[w] && dfs(w)) {
                match[u] = v;
                match[v] = u;
                return true;
            }
        }
        return false;
    }
    public int maxMatch() {
        int m = 0;
        Arrays.fill(match, -1);
        for (int i = 0; i < match.length; i++) {
            if (match[i] < 0) {
                Arrays.fill(used, false);
                if (dfs(i)) m++;
            }
        }
        return m;
    }
}

public class Asteroids {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), K = in.nextInt();
        BipartiteMatching bm = new BipartiteMatching(N * 2);
        for (int k = 0; k < K; k++) {
            bm.addEdge(in.nextInt() - 1, N + in.nextInt() - 1);
        }
        System.out.println(bm.maxMatch());
    }
}
