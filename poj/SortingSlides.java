import java.io.*;
import java.util.*;

class Slide {

	private int l;
	private int r;
	private int t;
	private int b;

    public Slide(int l, int r, int b, int t) { 
		this.l = l;
		this.r = r;
		this.b = b;
		this.t = t;
    }
    public boolean contains(int x, int y) {
        return x > l && x < r && y > b && y < t;
    }
}

class BipartiteMatching {
    int[] match;
    boolean[] used;
    List<Set<Integer>> graph;
    public BipartiteMatching(int N) { 
        match = new int[N];
        used = new boolean[N];
        graph = new ArrayList<Set<Integer>>(N);
        for (int i = 0; i < N; i++) graph.add(new HashSet<Integer>());
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

public class SortingSlides {
    private static int[] solve(BipartiteMatching bm, int N) {
        bm.maxMatch();
        int[] res = bm.match.clone();
        for (int i = 0; i < N; i++) {
            int v = res[i];
            bm.removeEdge(i, v);
            if (bm.maxMatch() == N) res[i] = -1;
            bm.addEdge(i, v);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c = 0;
        while (in.hasNextInt()) {
            int N = in.nextInt();
            if (N == 0) return;
            Slide[] slides = new Slide[N];
            for (int i = 0; i < N; i++) {
                slides[i] = new Slide(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
            }
            BipartiteMatching bm = new BipartiteMatching(N * 2);
            for (int i = 0; i < N; i++) {
                int x = in.nextInt(), y = in.nextInt();
                for (int j = 0; j < slides.length; j++) {
                    if (slides[j].contains(x, y)) {
                        bm.addEdge(j, N + i);
                    }
                }
            }
            int[] match = solve(bm, N);
            System.out.printf("Heap %d\n", ++c);
            boolean spaceFlag = false;
            for (int i = 0; i < N; i++) {
                if (match[i] < 0) continue;
                if (spaceFlag) System.out.print(' ');
                else spaceFlag = true;
                System.out.printf("(%c,%d)", (char)('A' + i), match[i] - N + 1);
            }
            if (spaceFlag) System.out.println();
            else System.out.println("none");
            System.out.println();
        }
    }
}
