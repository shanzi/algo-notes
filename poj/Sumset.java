import java.io.*;
import java.util.*;

public class Sumset {
    private static void solve(long[] array) {
        int N = array.length;
        HashSet<Long> set = new HashSet<Long>(N * (N - 1));
        int mindex = -1;
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < a; b++) {
                set.add(array[a] + array[b]);
            }

            int c = a + 1;
            for (int d = c + 1; d < N; d++) {
                if (set.contains(array[c] - array[d])) mindex = Math.max(mindex, c);
                if (set.contains(array[d] - array[c])) mindex = Math.max(mindex, d);
            }
        }
        set.clear();
        for (int a = N - 1; a >= 0; a--) {
            for (int b = a + 1; b < N; b++) {
                set.add(array[a] + array[b]);
            }

            int c = a - 1;
            for (int d = 0; d < c; d++) {
                if (set.contains(array[c] - array[d])) mindex = Math.max(mindex, c);
                if (set.contains(array[d] - array[c])) mindex = Math.max(mindex, d);
            }
        }
        if (mindex < 0) System.out.println("no solution");
        else System.out.println(array[mindex]);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true) {
            int N = in.nextInt();
            if (N == 0) break;
            TreeSet<Long> set = new TreeSet<Long>();
            for (int i = 0; i < N; i++) {
                set.add(in.nextLong());
            }
            long[] array = new long[set.size()];
            Iterator<Long> iter = set.iterator();
            for (int i = 0; i < array.length; i++) {
                array[i] = iter.next();
            }
            solve(array);
        }
    }
}
