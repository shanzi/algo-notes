import java.io.*;
import java.util.*;

public class Super2048 {
    static private int N;
    static private int[][] matrix;
    static private int direction;
    private static int getX(int x, int y) {
       if (direction == 1 || direction == 2) return x; 
       else if (direction == 3) return y;
       else return N - y - 1;
    }
    private static int getY(int x, int y) {
       if (direction == 1) return y; 
       else if (direction == 2) return N - y - 1; 
       else return x;
    }
    private static int get(int x, int y) {
        return matrix[getX(x, y)][getY(x, y)];
    }
    private static void set(int x, int y, int v) {
        matrix[getX(x, y)][getY(x, y)] = v;
    }
    private static void solve() {
        int N = matrix.length;
        for (int i = 0; i < N; i++) {
            int l = 0, b = 0;
            while (b < N) {
                if (get(i, b) != 0) set(i, l++, get(i, b));
                b++;
            }
            while (l < N) set(i, l++, 0);
            l = b = 0;
            while (b < N) {
                int c = get(i, b);
                if (b + 1 < N && get(i, b + 1) == c) {
                    set(i, l++, c * 2);
                    b += 2;
                } else {
                    set(i, l++, c);
                    b++;
                }
            }
            while (l < N) set(i, l++, 0);
        }

        for (int i = 0; i < N; i++) {
            System.out.printf("%d", matrix[i][0]);
            for (int j = 1; j < N; j++) {
                System.out.printf(" %d", matrix[i][j]);
            }
            System.out.println();
        }
    }    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            N = in.nextInt();
            String d = in.next();
            if (d.equals("left")) direction = 1;
            else if (d.equals("right")) direction = 2;
            else if (d.equals("up")) direction = 3;
            else direction = 4;
            matrix = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = in.nextInt();
                }
            }
            System.out.printf("Case #%d:\n", t + 1);
            solve();
        }
    }
}
