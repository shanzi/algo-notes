import java.io.*;
import java.util.*;

public class LakeCounting {
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static void markWater(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != 'W') return;
        board[x][y] = '.';
        for (int i = 0; i < 8; i++) {
            markWater(board, x + dx[i], y + dy[i]);
        }
    }
    private static void solve(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'W') {
                    markWater(board, i, j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        str = in.readLine().split(" ");
        int M = Integer.parseInt(str[0]), N = Integer.parseInt(str[1]);
        char[][] ground = new char[M][N];
        for (int i = 0; i < M; i++) {
            String s = in.readLine();
            for (int j = 0; j < N; j++) {
                ground[i][j] = s.charAt(j);
            }
        }
        solve(ground);
    }
}
