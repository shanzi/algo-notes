import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int compareTo(Edge b) {
        return this.weight - b.weight;
    }
}

public class Conscription {

    private static int[] ids;
    private static int[] sizes;

    private static void initUnionFind(int N) {
        ids = new int[N];
        sizes = new int[N];

        for (int i = 0; i < N; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }
    }

    private static int find(int p) {
        while (p != ids[p]) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }

        return p;
    }

    private static void union(int p, int q) {
        p = find(p);
        q = find(q);

        if (p == q) return;

        if (sizes[p] < sizes[q]) {
            ids[p] = q;
            sizes[q] += sizes[p];
        } else {
            ids[q] = p;
            sizes[p] += sizes[q];
        }
    }

    private static boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private static long kruskal(int N, Edge[] edges) {
        initUnionFind(N);
        Arrays.sort(edges);
        long cost = 0;
        for (Edge e : edges) {
            if (connected(e.u, e.v)) continue;
            cost += e.weight;
            union(e.u, e.v);
        }

        return cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        String[] str;

        for (int t = 0; t < T; t++) {
            String s = in.readLine();
            while(s.isEmpty()) s = in.readLine();

            str = s.split(" ");
            int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]), R = Integer.parseInt(str[2]);

            Edge[] edges = new Edge[R];

            for (int i = 0; i < R; i++) {
                str = in.readLine().split(" ");
                edges[i] = new Edge(Integer.parseInt(str[0]), N + Integer.parseInt(str[1]), -Integer.parseInt(str[2]));
            }

            System.out.println(10000 * (M + N) + kruskal(N + M, edges));
        }
    }
}
