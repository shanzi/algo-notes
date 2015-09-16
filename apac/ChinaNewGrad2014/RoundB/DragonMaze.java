import java.io.*;
import java.util.*;

public class DragonMaze {
    static private int[] dx = {-1, 0, 1, 0};
    static private int[] dy = {0, 1, 0, -1};
    static private int[] dist = new int[10000];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), M = in.nextInt();
            int sx = in.nextInt(), sy = in.nextInt();
            int ex = in.nextInt(), ey = in.nextInt();
            int[][] maze = new int[N][M];
            int[][] dist = new int[N][M];
            long[][] power = new long[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    maze[i][j] = in.nextInt();
                }
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            LinkedList<Integer> xque = new LinkedList<Integer>();
            LinkedList<Integer> yque = new LinkedList<Integer>();

            xque.addLast(sx);
            yque.addLast(sy);

            power[sx][sy] = maze[sx][sy];

            while (!xque.isEmpty()) {
                int x = xque.pollFirst();
                int y = yque.pollFirst();
                if (maze[x][y] < 0) continue;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && maze[nx][ny] >= 0 && dist[nx][ny] >= dist[x][y] + 1) {
                        dist[nx][ny] = dist[x][y] + 1;
                        power[nx][ny] = Math.max(power[nx][ny], power[x][y] + maze[nx][ny]);
                        xque.addLast(nx);
                        yque.addLast(ny);
                    }
                }
                if (x == ex && y == ey) break;
            }
            System.out.printf("Case #%d: ", t + 1);
            if (dist[ex][ey] >= Integer.MAX_VALUE) System.out.println("Mission Impossible.");
            else System.out.println(power[ex][ey]);
        }
    }
}
