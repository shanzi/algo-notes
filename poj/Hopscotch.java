import java.io.*;
import java.util.*;

public class Hopscotch {
    static private int[] dx = {-1, 0, 1, 0};
    static private int[] dy = {0, 1, 0, -1};
    static private int[][] matrix = new int[5][5];
    private static void findDigits(int x, int y, int n, int limit, Set<Integer> set) {
        if (x < 0 || y < 0 || x >= 5 || y >= 5) return;

        n = n * 10 + matrix[x][y];

        if (limit > 0) {
            for (int i = 0; i < 4; i++) {
                findDigits(x + dx[i], y + dy[i], n, limit - 1, set);
            }
        } else {
            set.add(n);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        HashSet<Integer> set = new HashSet<Integer>(4 << 6);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                findDigits(i, j, 0, 5, set);
            }
        }
        System.out.println(set.size());
    }
}
