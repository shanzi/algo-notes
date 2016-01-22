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
    public int maxMatch(int c) {
        int m = 0;
        Arrays.fill(match, -1);
        for (int i = 0; i < c; i++) {
            if (match[i] < 0) {
                Arrays.fill(used, false);
                if (dfs(i)) m++;
            }
        }
        return m;
    }
}

public class MuddyFields {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(), C = in.nextInt();
        boolean[][] boards = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String row = in.next();
            for (int j = 0; j < C; j++) {
                boards[i][j] = (row.charAt(j) == '*');
            }
        }
        int[][] numbering = new int[R][C];
        int counting = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C;) {
                if (boards[i][j]) {
                    counting++;
                    while (j < C && boards[i][j]) {
                        numbering[i][j] = counting;
                        j++;
                    }
                } else j++;
            }
        }
        BipartiteMatching bm = new BipartiteMatching(counting + R * C);
        for (int j = 0; j < C; j++) {
            for (int i = 0; i < R;) {
                if (boards[i][j]) {
                    counting++;
                    while (i < R && boards[i][j]) {
                        if (numbering[i][j] > 0) bm.addEdge(numbering[i][j] - 1, counting - 1);
                        i++;
                    }
                } else i++;
            }
        }
        System.out.println(bm.maxMatch(counting));
    }
}
