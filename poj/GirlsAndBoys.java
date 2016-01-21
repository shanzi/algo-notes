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

public class GirlsAndBoys {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        String line;
        while ((line = in.readLine()) != null) {
            if (line.isEmpty()) continue;
            int N = Integer.parseInt(line);
            BipartiteMatching bm = new BipartiteMatching(N);
            for (int i = 0; i < N; i++) {
                str = in.readLine().split("[^0-9]+");
                int a = Integer.parseInt(str[0]);
                int an = Integer.parseInt(str[1]);
                for (int j = 0; j < an; j++) {
                    int b = Integer.parseInt(str[j + 2]);
                    bm.addEdge(a, b);
                }
            }
            int m = bm.maxMatch();
            System.out.println(N - m);
        }
    }
}
