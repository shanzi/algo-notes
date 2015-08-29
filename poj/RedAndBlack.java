import java.io.*;
import java.util.*;

public class RedAndBlack {
    static private int[] dx = {-1, 0, 1, 0};
    static private int[] dy = {0, 1, 0, -1};
    private static int solve(char[][] ground, int px, int py) {
        if (px < 0 || py < 0 || px >= ground.length || py >= ground[0].length || ground[px][py] != '.') return 0;

        int count = 1;
        ground[px][py] = '#';
        for (int i = 0; i < 4; i++) {
            count += solve(ground, px + dx[i], py + dy[i]);
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        while (true) {
            str = in.readLine().split(" ");
            int M = Integer.parseInt(str[0]), N = Integer.parseInt(str[1]);
            if (M == 0 && N == 0) return;
            char[][] ground = new char[N][M];
            int px = -1, py = -1;
            for (int i = 0; i < N; i++) {
                String s = in.readLine();
                for (int j = 0; j < M; j++) {
                    ground[i][j] = s.charAt(j);
                    if (ground[i][j] == '@') {
                        px = i;
                        py = j;
                        ground[i][j] = '.';
                    }
                }
            }
            System.out.println(solve(ground, px, py));
        }
    }
}
