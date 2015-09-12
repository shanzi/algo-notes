import java.io.*;
import java.util.*;

public class ItzChess {
    private static int kingKillingCount(char[][] board, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (x + i >= 0 && x + i < 8 && y + j >= 0 && y + j < 8 && board[x + i][y + j] != 0) count++;
            }
        }
        return count;
    }
    private static int queenKillingCount(char[][] board, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                for (int k = 1; k < 8; k++) {
                    int nx = x + i * k, ny = y + j * k;
                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) break;
                    if (board[nx][ny] != 0) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
    private static int rookKillingCount(char[][] board, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i == 0 && j == 0) || (i != 0 && j != 0)) continue;
                for (int k = 1; k < 8; k++) {
                    int nx = x + i * k, ny = y + j * k;
                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) break;
                    if (board[nx][ny] != 0) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
    private static int bishopKillingCount(char[][] board, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 || j == 0) continue;
                for (int k = 1; k < 8; k++) {
                    int nx = x + i * k, ny = y + j * k;
                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) break;
                    if (board[nx][ny] != 0) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
    private static int knightsKillingCount(char[][]board, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 || j == 0) continue;
                for (int k = 1; k <= 2; k++) {
                    int nx = x + k * i;
                    int ny = y + (3 - k) * j;
                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue;
                    if (board[nx][ny] != 0) count++;
                }
            }
        }
        return count;
    }
    private static int pawnKillingCount(char[][] board, int x, int y) {
        int count = 0;
        for (int j = -1; j <= 1; j++) {
            if (j == 0) continue;
            if (x + 1 < 8 && y + j >= 0 && y + j < 8 && board[x + 1][y + j] != 0) count++;
        }
        return count;
    }
    private static int solve(char[][] board) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 0) continue;
                else if (board[i][j] == 'K') count += kingKillingCount(board, i, j);
                else if (board[i][j] == 'Q') count += queenKillingCount(board, i, j);
                else if (board[i][j] == 'R') count += rookKillingCount(board, i, j);
                else if (board[i][j] == 'B') count += bishopKillingCount(board, i, j);
                else if (board[i][j] == 'N') count += knightsKillingCount(board, i, j);
                else if (board[i][j] == 'P') count += pawnKillingCount(board, i, j);
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            char[][] board = new char[8][8];
            int N = in.nextInt();
            for (int i = 0; i < N; i++) {
                String piece = in.next();
                int x = piece.charAt(0) - 'A';
                int y = piece.charAt(1) - '1';
                board[x][y] = piece.charAt(3);
            }
            System.out.printf("Case #%d: %d\n", t + 1, solve(board));
        }
    }
}
