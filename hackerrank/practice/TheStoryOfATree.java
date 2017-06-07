import java.io.*;
import java.util.*;

class EdgeList extends ArrayList<Integer>{};

public class TheStoryOfATree {
    static long L = 100000000;
    private static int solve(EdgeList[] graph, HashSet<Long> guess, int K) {
        int[] guessed = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> que = new LinkedList<Integer>();
        que.add(0);

        while (!que.isEmpty()) {
            int u = que.poll();
            visited[u] = true;
            for (Integer v : graph[u]) {
                if (visited[v]) continue;
                que.add(v);
                if (guess.contains(u * L + v)) guessed[0]++;
            }
        }

        Arrays.fill(visited, false);

        que.add(0);
        while (!que.isEmpty()) {
            int u = que.poll();
            visited[u] = true;
            for (Integer v : graph[u]) {
                if (visited[v]) continue;

                que.add(v);
                guessed[v] = guessed[u];
                if (guess.contains(u * L + v)) {
                    guessed[v]--;
                }
                if (guess.contains(v * L + u)) {
                    guessed[v]++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < guessed.length; i++) {
            if (guessed[i] >= K) count++;
        }

        return count;
    }
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int Q = in.nextInt();
        for (int q = 0; q < Q; q++) {
            int N = in.nextInt();
            EdgeList[] graph = new EdgeList[N];

            for (int i = 0; i < N; i++) {
                graph[i] = new EdgeList();
            }

            for (int i = 1; i < N; i++) {
                int u = in.nextInt() - 1, v = in.nextInt() - 1;
                graph[u].add(v);
                graph[v].add(u);
            }

            int M = in.nextInt();
            int K = in.nextInt();
            HashSet<Long> guess = new HashSet<Long>(M);
            for (int i = 0; i < M; i++) {
                int u = in.nextInt() - 1, v = in.nextInt() - 1;
                guess.add(L * u + v);
            }

            int count = solve(graph, guess, K);
            int d = gcd(count, N);
            System.out.printf("%d/%d\n", count / d, N / d);
        }
    }
}
