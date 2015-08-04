import java.io.*;
import java.util.*;

public class ASimpleProblemWithIntegersBIT {
    private static long[] bitsum;
    private static long[] bitadd;

    private static void makeBIT(long[] array) {
        bitsum = array;
        int jump = 2;
        while (jump < bitsum.length) {
            for (int i = jump; i < bitsum.length; i += jump) {
                bitsum[i] += bitsum[i - (jump >> 1)];
            }
            jump <<= 1;
        }
        bitadd = new long[array.length];
    } 

    private static void add(long[] bit, int i, long x) {
        while (i > 0 && i < bit.length) {
            bit[i] += x;
            i += i & -i;
        }
    }

    private static void add(int l, int r, long x) {
        add(bitsum, l, -x * (l - 1));
        add(bitsum, r + 1, x * r);
        add(bitadd, l, x);
        add(bitadd, r + 1, -x);
    }

    private static long query(long[] bit, int i) {
        long res = 0;
        while (i > 0 && i < bit.length) {
            res += bit[i];
            i -= i & -i;
        }
        return res;
    }

    private static long query(int l, int r) {
        long res = query(bitadd, r) * r + query(bitsum, r);
        res -= query(bitadd, l - 1) * (l - 1) + query(bitsum, l - 1);
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(), Q = in.nextInt();

            long[] array = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                array[i] = in.nextLong();
            }

            makeBIT(array);

            for (int i = 0; i < Q; i++) {
                String c = in.next();
                if (c.equals("Q")) {
                    int l = in.nextInt(), r = in.nextInt();
                    System.out.println(query(l, r));
                } else {
                    int l = in.nextInt(), r = in.nextInt();
                    long x = in.nextLong();
                    add(l, r, x);
                }
            }
        }
    }
}
