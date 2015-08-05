import java.io.*;
import java.util.*;

public class Matrix {
    private static void addCol(int[] bit, int y, int v) {
        while (y > 0 && y < bit.length) {
            bit[y] += v;
            y += y & -y;
        }
    }

    private static void addRow(int[][] bit2d, int x, int y1, int y2, int v) {
        while (x > 0 && x < bit2d.length) {
            addCol(bit2d[x], y1, v);
            addCol(bit2d[x], y2 + 1, -v);
            x += x & -x;
        }
    }

    private static void add(int[][] bit2d, int x1, int y1, int x2, int y2) {
        addRow(bit2d, x1, y1, y2, 1);
        addRow(bit2d, x2 + 1, y1, y2, -1);
    }

    private static int queryCol(int[] bit, int y) {
        int res = 0;
        while (y > 0 && y < bit.length) {
            res += bit[y];
            y -= y & -y;
        }
        return res;
    }

    private static int query(int[][] bit2d, int x, int y) {
        int res = 0;
        while (x > 0 && x < bit2d.length) {
            res += queryCol(bit2d[x], y);
            x -= x & -x;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int ncase = in.nextInt();
            for (int i = 0; i < ncase; i++) {
                if (i > 0) System.out.println();
                int N = in.nextInt(), T = in.nextInt();
                int[][] bit2d = new int[N + 1][N + 1];

                for (int j = 0; j < T; j++) {
                    String op = in.next();
                    if (op.equals("C")) {
                        int x1 = in.nextInt(), y1 = in.nextInt(), x2 = in.nextInt(), y2 = in.nextInt();
                        add(bit2d, x1, y1, x2, y2);
                    } else {
                        int x = in.nextInt(), y =in.nextInt();
                        System.out.println(query(bit2d, x, y) & 1);
                    }
                }
            }
        }
    }
}
