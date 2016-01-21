import java.io.*;
import java.util.*;

class BipartiteMatching {
    int[] match;
    boolean[] used;
    List<Set<Integer>> graph;
    public BipartiteMatching(int N) { 
        match = new int[N];
        used = new boolean[N];
        graph = new ArrayList<Set<Integer>>(N);
        for (int i = 0; i < N; i++) graph.add(new HashSet<Integer>(N));
    }
    public void addEdge(int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
    public void removeEdge(int u, int v) {
        graph.get(u).remove(v);
        graph.get(v).remove(u);
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

public class Kindergarden {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseno = 0;
        while (true) {
            int G = in.nextInt(), B = in.nextInt(), M = in.nextInt();
            if (G == 0 && B == 0 && M == 0) return;
            BipartiteMatching bm = new BipartiteMatching(G + B);
            for (int i = 0; i < G; i++) {
                for (int j = 0; j < B; j++) {
                    bm.addEdge(i, G + j);
                }
            }
            for (int i = 0; i < M; i++) {
                bm.removeEdge(in.nextInt() - 1, G + in.nextInt() - 1);
            }
            System.out.printf("Case %d: %d\n", ++caseno, G + B - bm.maxMatch());
        }
    }
}
