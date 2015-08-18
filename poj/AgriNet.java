import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int d;

    public Edge(int u, int v, int d) {
        this.u = u;
        this.v = v;
        this.d = d;
    }

    public int compareTo(Edge b) {
        return this.d - b.d;
    }
}

public class AgriNet {
    private static int prim(int[][] costs) {
        int N = costs.length;

        int sum = 0;
        boolean[] used = new boolean[N];
        used[0] = true;

        PriorityQueue<Edge> que = new PriorityQueue<Edge>(N);
        for (int i = 1; i < N; i++) que.offer(new Edge(0, i, costs[0][i]));

        while (!que.isEmpty()) {
            Edge e = que.poll();

            if (used[e.v]) continue;

            sum += e.d;
            used[e.v] = true;

            for (int i = 0; i < N; i++) {
                if (used[i]) continue;
                que.offer(new Edge(e.v, i, costs[e.v][i]));
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            int[][] costs = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    costs[i][j] = in.nextInt();
                }
            }

            System.out.println(prim(costs));
        }
    }
}
