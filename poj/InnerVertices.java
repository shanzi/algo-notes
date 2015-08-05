import java.io.*;
import java.util.*;

public class InnerVertices {
    private static int[] compressY(int[][] vertices) {
        TreeSet<Integer> sortSet = new TreeSet<Integer>();
        for (int i = 0; i < vertices.length; i++) {
            sortSet.add(vertices[i][1]);
        }
        int[] Y = new int[sortSet.size()];
        int i = 0;
        Iterator<Integer> iter = sortSet.iterator();
        while (iter.hasNext()) {
            Y[i++] = iter.next();
        }

        return Y;
    }

    private static long query(long[] bitcount, int y) {
        long res = 0;
        while (y > 0 && y < bitcount.length) {
            res += bitcount[y];
            y -= y & -y;
        }
        return res;
    }

    private static void add(long[] bitcount, int y, int v) {
        while (y > 0 && y < bitcount.length) {
            bitcount[y] += v;
            y += y & -y;
        }
    }

    private static void solve(int[][] vertices) {
        Arrays.sort(vertices, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) return a[0] - b[0];
                else return a[1] - b[1];
            }
        });

        int[] compressedY = compressY(vertices);
        long[] ycount = new long[compressedY.length + 1];
        long[] bitcount = new long[compressedY.length + 1];

        int i = 0;
        long res = 0;
        while (i < vertices.length) {
            int j = i;
            int miny = Integer.MAX_VALUE, maxy = 0;
            while (j < vertices.length && vertices[j][0] == vertices[i][0]) {
                int x = vertices[j][0], y = Arrays.binarySearch(compressedY, vertices[j][1]) + 1;
                long count = query(bitcount, y) + 1;

                if (ycount[y] == 0) {
                    res += 1;
                } else {
                    res += count - ycount[y];
                }
                ycount[y] = count;

                miny = Math.min(miny, y);
                maxy = y;
                j++;
            }

            add(bitcount, miny, 1);
            add(bitcount, maxy + 1, -1);
            i = j;
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            int[][] vertices = new int[N][2];

            for (int i = 0; i < N; i++) {
                vertices[i][0] = in.nextInt();
                vertices[i][1] = in.nextInt();
            }

            solve(vertices);
        }
    }
}
