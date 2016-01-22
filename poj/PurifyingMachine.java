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

public class PurifyingMachine {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int N = in.nextInt(), M = in.nextInt();
            if (N == 0 && M == 0) return;
            int W = 2 << N;
            boolean[] infected = new boolean[W];
            for (int i = 0; i < M; i++) {
                String s = in.next();
                infected[Integer.parseInt(s.replace('*', '0'), 2)] = true;
                infected[Integer.parseInt(s.replace('*', '1'), 2)] = true;
            }
            BipartiteMatching bm = new BipartiteMatching(W);
            int opcount = 0;
            for (int i = 0; i < W; i++) {
                if (infected[i]) opcount++;
                for (int j = 0; j < N; j++) {
                    int a = i;
                    int b = i ^ (1 << j);
                    if (infected[a] && infected[b]) {
                        bm.addEdge(a, b);
                    }
                }
            }
            System.out.println(opcount - bm.maxMatch());
        }
    }
}
