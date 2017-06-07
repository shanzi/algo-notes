import java.io.*;
import java.util.*;


class EdgeList extends ArrayList<Integer>{};

public class CityConstruction {
    static EdgeList[] graph = new EdgeList[5005];
    static EdgeList[] revgraph = new EdgeList[5005];
    static int[] visited = new int[5005];

    private static void addEdge(int u, int v) {
        if (graph[u] == null) {
            graph[u] = new EdgeList();
        }
        graph[u].add(v);
        if (revgraph[v] == null) { 
            revgraph[v] = new EdgeList();
        }
        revgraph[v].add(u);
    }

    private static boolean connected(int u, int v) {
        Arrays.fill(visited, 0);
        Queue<Integer> que = new LinkedList<Integer>();
        Queue<Integer> revque = new LinkedList<Integer>();
        que.add(u);
        revque.add(v);

        visited[u] = 1;
        visited[v] = -1;

        while (!que.isEmpty() || !revque.isEmpty()) {
            if (!revque.isEmpty() && que.size() > revque.size() || que.isEmpty()) {
                int x = revque.poll();
                if (revgraph[x] == null) continue;
                for (Integer y : revgraph[x]) {
                    if (visited[y] == 1) return true;
                    if (visited[y] == 0) {
                        revque.add(y);
                        visited[y] = -1;
                    }
                }
            } else {
                System.out.println(que.size());
                int x = que.poll();
                if (graph[x] == null) continue;
                for (Integer y : graph[x]) {
                    if (visited[y] == -1) return true;
                    if (visited[y] == 0) {
                        que.add(y);
                        visited[y] = 1;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int N = in.nextInt(), M = in.nextInt();

        for (int i = 0; i < M; i++) {
            int u = in.nextInt() - 1, v = in.nextInt() - 1;
            addEdge(u, v);
        }

        int Q = in.nextInt();
        for (int q = 0; q < Q; q++) {
            int op = in.nextInt(), x = in.nextInt(), y = in.nextInt();
            if (op == 1) {
                if (y == 0) addEdge(x - 1, N++);
                else addEdge(N++, x - 1);
            } else {
                System.out.println(connected(x - 1, y - 1) ? "Yes" : "No");
            }
        }
    }
}
