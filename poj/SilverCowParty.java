import java.io.*;
import java.util.*;

class Edge {
    int u;
    int v;
    int cost;

    public Edge(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}

class Distance implements Comparable<Distance> {
    int u;
    int d;

    public Distance(int u, int d) {
        this.u = u;
        this.d = d;
    }

    public int compareTo(Distance another) {
        return this.d - another.d;
    }
}

public class SilverCowParty {
    private static int INF = Integer.MAX_VALUE;

    private static int[] getDist(int N, int s, List<List<Edge>> graph) {
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        PriorityQueue<Distance> que = new PriorityQueue<Distance>();
        que.offer(new Distance(s, 0));

        while (!que.isEmpty()) {
            Distance p = que.poll();
            if (dist[p.u] < p.d) continue;

            for (Edge e : graph.get(p.u)) {
                if (dist[e.v] > p.d + e.cost) {
                    dist[e.v] = p.d + e.cost;
                    que.offer(new Distance(e.v, dist[e.v]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] str = in.readLine().split(" ");

        int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]), X = Integer.parseInt(str[2]) - 1;

        ArrayList<List<Edge>> graph = new ArrayList<List<Edge>>(N);
        for (int i = 0; i < N; i++) graph.add(new ArrayList<Edge>());

        for (int i = 0; i < M; i++) {
            str = in.readLine().split(" ");
            int a = Integer.parseInt(str[0]) - 1, b = Integer.parseInt(str[1]) - 1, cost = Integer.parseInt(str[2]);
            graph.get(a).add(new Edge(a, b, cost));
        }

        int[] xdist = getDist(N, X, graph);
        int max = 0;

        for (int i = 0; i < N; i++) {
            if (i == X) continue;

            int[] idist = getDist(N, i, graph);
            max = Math.max(max, idist[X] + xdist[i]);
        }

        System.out.println(max);
    }
}
