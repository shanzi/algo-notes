import java.io.*;
import java.util.*;

public class Curling2 {
    static private int[] dx = {-1, 0, 1, 0};
    static private int[] dy = {0, 1, 0, -1};
    private static int findSolution(int[][] board, int sx, int sy, int d, int limit) {
        if (limit >= 10) return Integer.MAX_VALUE;

        int h = board.length;
        int w = board[0].length;

        int nx, ny;

        boolean flag = false;

        while (true) {
            nx = sx + dx[d];
            ny = sy + dy[d];

            if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                return Integer.MAX_VALUE;
            }

            if (board[nx][ny] == 3) {
                return 1;
            }

            if (board[nx][ny] == 1) {
                if (!flag) return Integer.MAX_VALUE;
                break;
            }

            sx = nx;
            sy = ny;
            flag = true;
        }

        board[nx][ny] = 0;
        int min = Integer.MAX_VALUE;
        for (int direction = 0; direction < 4; direction++) {
            int res = findSolution(board, sx, sy, direction, limit + 1);
            min = Math.min(min, res);
        }
        board[nx][ny] = 1;
        if (min == Integer.MAX_VALUE) return min;
        else return min + 1;
    }
    private static void solve(int[][] board, int sx, int sy) {
        int solution = Integer.MAX_VALUE;
        for (int direction = 0; direction < 4; direction++) {
            int res = findSolution(board, sx, sy, direction, 0);
            solution = Math.min(solution, res);
        }

        if (solution == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(solution);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int W = in.nextInt(), H = in.nextInt();
            if (W == 0 && H == 0) return;
            int[][] board = new int[H][W];
            int sx = -1, sy = -1;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    board[i][j] = in.nextInt();
                    if (board[i][j] == 2) {
                        board[i][j] = 0;
                        sx = i;
                        sy = j;
                    }
                }
            }

            solve(board, sx, sy);
        }
    }
}
