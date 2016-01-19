import java.io.*;
import java.util.*;

class NetworkFlow {
    private static class NFEdge {
        int v;
        int rev;
        int cap;
        public NFEdge(int v, int rev, int cap) { 
            this.v = v;
            this.rev = rev;
            this.cap = cap;
        }
    }
    private boolean[] visited;
    private List<List<NFEdge>> graph;
    public NetworkFlow(int N) { 
        visited = new boolean[N];
        graph = new ArrayList<List<NFEdge>>(N);
        for (int i = 0; i < N; i++) graph.add(new ArrayList<NFEdge>());
    }
    private int dfs(int u, int t, int cap) {
        if (u == t) return cap;
        visited[u] = true;
        for (NFEdge e: graph.get(u)) {
            if (e.cap > 0 && !visited[e.v]) {
                int mcap = dfs(e.v, t, Math.min(cap, e.cap));
                if (mcap > 0) {
                    e.cap -= mcap;
                    graph.get(e.v).get(e.rev).cap += mcap;
                    return mcap;
                }
            }
        }
        return 0;
    }
    public int maxFlow(int start, int target) {
        int flow = 0;
        while (true) {
            Arrays.fill(visited, false);
            int mcap = dfs(start, target, Integer.MAX_VALUE);
            if (mcap <= 0) break;
            else flow += mcap;
        }
        return flow;
    }
    public void addEdge(int u, int v, int cap) {
        graph.get(u).add(new NFEdge(v, graph.get(v).size(), cap));
        graph.get(v).add(new NFEdge(u, graph.get(u).size() - 1, 0));
    }
}

public class Hex {
    static private int INF = 10000;
    static private int[] dx = {-1, -1, 0, 1, 1, 0};
    static private int[] dy = {0, 1, 1, 0, -1, -1};
    private static String solve(char[][] board, int N) {
        NetworkFlow nf = new NetworkFlow(N * N * 2 + 4);
        int redcount = 0, bluecount = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                nf.addEdge(x * N + y, N * N + x * N + y, 1);
                if (board[x][y] == '.') continue;

                if (board[x][y] == 'B') bluecount++;
                else if (board[x][y] == 'R') redcount++;

                for (int i = 0; i < 6; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && board[x][y] == board[nx][ny]) {
                        nf.addEdge(N * N + x * N + y, nx * N + ny, INF);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (board[i][0] == 'B') nf.addEdge(N * N * 2, i * N, INF);
            if (board[i][N - 1] == 'B') nf.addEdge(N * N + i * N + N - 1, N * N * 2 + 1, INF);
            if (board[0][i] == 'R') nf.addEdge(N * N * 2 + 2, i, INF);
            if (board[N - 1][i] == 'R') nf.addEdge(N * N + N * (N - 1) + i, N * N * 2 + 3, INF);
        }

        int a = nf.maxFlow(N * N * 2, N * N * 2 + 1);
        int b = nf.maxFlow(N * N * 2 + 2, N * N * 2 + 3);

        if (Math.abs(redcount - bluecount) > 1) return "Impossible";
        else if (a == 0 && b == 0) return "Nobody wins";
        else if (a == 1 && bluecount >= redcount) return "Blue wins";
        else if (b == 1 && redcount >= bluecount) return "Red wins";
        else return "Impossible";
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            char[][] board = new char[N][N];

            for (int i = 0; i < N; i++) {
                String row = in.next();
                for (int j = 0; j < N; j++) {
                    board[i][j] = row.charAt(j);
                }
            }

            System.out.printf("Case #%d: %s\n", t + 1, solve(board, N));
        }
    }
}
