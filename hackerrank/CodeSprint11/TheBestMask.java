import java.io.*;
import java.util.*;

public class TheBestMask {
    private static int popcount(long n) {
        int c = 0;
        while (n != 0) {
            n ^= n & -n;
            c++;
        }
        return c;
    }
    private static long findMask(long[] arr, long mask, long bitmask) {
        while(true) {
            long max = 0;
            boolean flag = true;

            for (int i = 0; i < arr.length; i++) {
                long v = arr[i] & bitmask;
                if (v == 0 || (mask & v) != 0) continue;
                max = Math.max(max, v & -v);
                flag = false;
            }
            mask |= max;
            if (flag) break;
        }
        return mask;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextLong();
        }

        long res = 0;
        int minp = Integer.MAX_VALUE;

        for (int i = 0; i <= 26; i++) {
            long bitmask = (-1 << i);
            long highmask = findMask(arr, 0, bitmask);
            long mask = findMask(arr, highmask, ~bitmask);
            int p = popcount(mask);
            if (p < minp) {
                minp = p;
                res = mask;
            }
        }
        System.out.println(res);
    }
}
