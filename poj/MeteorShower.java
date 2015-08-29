import java.io.*;
import java.util.*;

class Position{
    int x;
    int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class MeteorShower {
    static private int[] dx = {1, 0, -1, 0};
    static private int[] dy = {0, 1, 0, -1};
    private static void solve(int[][] ground) {
        LinkedList<Position> queue = new LinkedList<Position>();
        int time = 0;
        queue.addLast(new Position(0, 0));
        queue.addLast(null);

        while (!queue.isEmpty()) {
            Position p = queue.pollFirst();
            if (p == null) {
                time++;
                if (!queue.isEmpty()) queue.addLast(null);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i], ny = p.y + dy[i];
                if (nx < 0 || ny < 0) continue;
                if (ground[nx][ny] <= (time + 1)) continue;
                if (ground[nx][ny] == Integer.MAX_VALUE) {
                    System.out.println(time + 1);
                    return;
                }
                queue.addLast(new Position(nx, ny));
                ground[nx][ny] = 0;
            }
        }
        System.out.println(-1);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] ground = new int[305][305];
        for (int i = 0; i < 305; i++) Arrays.fill(ground[i], Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            int x = in.nextInt(), y = in.nextInt(), t = in.nextInt();
            ground[x][y] = Math.min(ground[x][y], t);
            if (x > 0) ground[x - 1][y] = Math.min(ground[x - 1][y], t);
            if (y > 0) ground[x][y - 1] = Math.min(ground[x][y - 1], t);
            ground[x + 1][y] = Math.min(ground[x + 1][y], t);
            ground[x][y + 1] = Math.min(ground[x][y + 1], t);
        }
        solve(ground);
    }
}
