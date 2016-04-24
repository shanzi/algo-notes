import java.io.*;
import java.util.*;


public class DynamicGrid {
    static private int R;
    static private int C;
    static private int[][] board = new int[100][100];
    static private boolean[][] visited = new boolean[100][100];
    static private int[] dx = {0, 0, 1, -1};
    static private int[] dy = {1, -1, 0, 0};
    private static int count() {
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] || board[i][j] == 0) continue;
                count++;
                Queue<Integer> que = new LinkedList<Integer>();
                que.offer(i * C + j);
                while (!que.isEmpty()) {
                    int n = que.poll();
                    visited[n / C][n % C] = true;
                    for (int k = 0; k < 4; k++) {
                        int x = n / C + dx[k], y = n % C + dy[k];
                        if (x < 0 || x >= R || y < 0 || y >= C 
                                || visited[x][y] || board[x][y] == 0) continue;
                        que.offer(x * C + y);
                    }
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            System.out.printf("Case #%d:\n", t + 1);
            R = in.nextInt();
            C = in.nextInt();
            for (int i = 0; i < R; i++) {
                String row = in.next();
                for (int j = 0; j < C; j++) {
                    board[i][j] = (row.charAt(j) - '0');
                }
            }
            int N = in.nextInt();
            for (int i = 0; i < N; i++) {
                String op = in.next();
                if (op.equals("Q")) {
                    System.out.println(count());
                } else {
                    int x = in.nextInt(), y = in.nextInt(), z = in.nextInt();
                    board[x][y] = z;
                }
            }
        }
    }
}
