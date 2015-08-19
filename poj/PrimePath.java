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

public class PrimePath {
    private static int[] getPrimes() {
        boolean[] a = new boolean[10000];
        for (int i = 3; i < a.length; i += 2) {
            if (a[i]) continue;

            for (int j = i; j * i < a.length; j += 2) {
                a[i * j] = true;
            }
        }

        int count = 0;
        for (int i = 1001; i < a.length; i += 2) {
            if (!a[i]) count++;
        }

        int[] res = new int[count];

        int h = 0;

        for (int i = 1001; i < a.length; i += 2) {
            if (!a[i]) res[h++] = i;
        }

        return res;
    }

    private static boolean connected(int a, int b) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            if ((a % 10) != (b % 10)) count++;
            a /= 10;
            b /= 10;
        }

        return count == 1;
    }

    private static List<List<Edge>> getGraph(int[]primes) {
        int N = primes.length;
        ArrayList<List<Edge>> graph = new ArrayList<List<Edge>>(N);
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (connected(primes[i], primes[j])) {
                    graph.get(i).add(new Edge(i, j, 1));
                    graph.get(j).add(new Edge(j, i, 1));
                }
            }
        }

        return graph;
    }

    private static int[] dijkstra(int N, int s, List<List<Edge>> graph) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
    
        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
        que.offer(new Edge(s, s, 0));
    
        while (!que.isEmpty()) {
            Edge p = que.poll();
            if (dist[p.u] < p.cost) continue;
    
            for (Edge e : graph.get(p.u)) {
                if (dist[e.v] > p.cost + e.cost) {
                    dist[e.v] = p.cost + e.cost;
                    que.offer(new Edge(e.v, s, dist[e.v]));
                }
            }
        }
    
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        int N = Integer.parseInt(in.readLine());

        int[] primes = getPrimes();
        List<List<Edge>> graph = getGraph(primes);

        for (int i = 0; i < N; i++) {
            str = in.readLine().split(" ");
            int a = Integer.parseInt(str[0]), b = Integer.parseInt(str[1]);

            a = Arrays.binarySearch(primes, a);
            b = Arrays.binarySearch(primes, b);

            int[] dist = dijkstra(primes.length, a, graph);

            if (a < 0 || b < 0 ||  dist[b] > 1000) {
                System.out.println("Impossible");
            } else {
                System.out.println(dist[b]);
            }
        }
    }
}
