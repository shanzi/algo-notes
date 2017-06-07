import java.io.*;
import java.util.*;

public class KingdomDivision {
    static private long MOD = 1000000007;
    static private int[] parent;
    static private ArrayList<ArrayList<Integer>> graph;
    static private ArrayList<Integer> seq;
    static private long[] dpsingle;
    static private long[] dpall;
    
    private static void BFS() {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        parent[0] = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            seq.add(u);
            for (Integer v: graph.get(u)) {
                if (parent[v] < 0) {
                    parent[v] = u;
                    queue.add(v);
                }
            }
        }
    }

    private static void DP() {
        for (int i = seq.size() - 1; i > 0; i--) {
            int u = seq.get(i);
            int v = parent[u];

            long all = dpall[u];
            long single = dpsingle[u];

            long sum = (2 * all % MOD - single % MOD + MOD) % MOD;
            dpall[v] = dpall[v] * sum % MOD;
            dpsingle[v] = (dpsingle[v] * ((all - single + MOD) % MOD)) % MOD;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        parent = new int[N];
        dpsingle = new long[N];
        dpall = new long[N];

        graph = new ArrayList<ArrayList<Integer>>(N);
        seq = new ArrayList<Integer>(N);
        for (int i = 0; i < N; i++) graph.add(new ArrayList<Integer>());


        Arrays.fill(parent, -1);
        Arrays.fill(dpall, 1);
        Arrays.fill(dpsingle, 1);


        for (int i = 0; i < N - 1; i++) {
            int u = in.nextInt() - 1, v = in.nextInt() - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        if (N == 1) {
            System.out.println(2);
        } else {
            BFS();

            DP();

            long res = (dpall[0] - dpsingle[0] + MOD) * 2 % MOD;
            System.out.println(res);
        }
    }
}
