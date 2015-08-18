import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int cost;

    public Edge(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }

    public int compareTo(Edge b) {
        return this.cost - b.cost;
    }
}

public class BadCowtractors {
    private static int prim(int N, List<List<Edge>> edges) {
        int sum = 0;
        boolean[] used = new boolean[N];
        PriorityQueue<Edge> que = new PriorityQueue<Edge>(N);

        used[0] = true;
        que.addAll(edges.get(0));

        while (!que.isEmpty()) {
            Edge e = que.poll();

            if (used[e.v]) continue;

            sum += e.cost;
            used[e.v] = true;

            for (Edge to : edges.get(e.v)) {
                if (used[to.v]) continue;
                que.offer(to);
            }
        }

        for (boolean u : used) {
           if (!u) return 1; 
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] str = in.readLine().split(" ");

        int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]);

        ArrayList<List<Edge>> edges = new ArrayList<List<Edge>>(N);
        for (int i = 0; i < N; i++) edges.add(new ArrayList<Edge>(N));

        for (int i = 0; i < M; i++) {
            str = in.readLine().split(" ");
            int u = Integer.parseInt(str[0]) - 1;
            int v = Integer.parseInt(str[1]) - 1;
            int cost = -Integer.parseInt(str[2]);

            edges.get(u).add(new Edge(u, v, cost));
            edges.get(v).add(new Edge(v, u, cost));
        }

        System.out.println(-prim(N, edges));
    }
}
