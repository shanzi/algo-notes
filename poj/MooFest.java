import java.io.*;
import java.util.*;

public class MooFest {
    private static void add(long[] bit, int i, int x) {
        while (i > 0 && i < bit.length) {
            bit[i] += x;
            i += i & -i;
        }
    }

    private static long query(long[] bit, int i) {
        long res = 0;
        while (i > 0 && i < bit.length) {
            res += bit[i];
            i -= i & -i;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int N = in.nextInt();
            int[][] cows = new int[N][2];
            for (int i = 0; i < N; i++) {
                cows[i][0] = in.nextInt();
                cows[i][1] = in.nextInt();
            }

            Arrays.sort(cows, new Comparator<int[]> () {
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            });

            long[] bitcount = new long[20001];
            long[] bitdistance = new long[20001];

            long res = 0;
            for (int i = 0; i < N; i++) {
                int vol = cows[i][0];
                int x = cows[i][1];
                long leftCount = query(bitcount, x);
                long rightCount = i - leftCount;
                long leftDistance = query(bitdistance, x);
                long rightDistance = query(bitdistance, 20000) - leftDistance;
                res += (leftCount * x - leftDistance + rightDistance - rightCount * x) * vol;
                add(bitcount, x, 1);
                add(bitdistance, x, x);
            }
            System.out.println(res);
        }
    }
}
