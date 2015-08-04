import java.io.*;
import java.util.*;

public class ASimpleProblemWithIntegers {
    private static int L;
    private static long[] rmqsum;
    private static long[] rmqadd;

    private static void makeRMQ(long[] array) {
        L = 1;
        while(L < array.length) L <<= 1;

        rmqsum = new long[L * 2 - 1];
        rmqadd = new long[L * 2 - 1];

        for (int i = 0; i < array.length; i++) {
            rmqsum[i + L - 1] = array[i];
        }

        for (int i = L - 2; i >= 0; i--) {
            rmqsum[i] = rmqsum[i * 2 + 1] + rmqsum[i * 2 + 2];
        }
    } 

    private static void add(int l, int r, long x, int k, int kl, int kr) {
        if (l <= kl && kr <= r) {
            rmqadd[k] += x;
        } else if (l <= kr && kl <= r) {
            rmqsum[k] += (Math.min(r, kr) - Math.max(l, kl) + 1) * x;
            add(l, r, x, k * 2 + 1, kl, (kl + kr) / 2);
            add(l, r, x, k * 2 + 2, (kl + kr) / 2 + 1, kr);
        }
    }

    private static long query(int l, int r, int k, int kl, int kr) {
        if (l > kr || r < kl) {
            return 0;
        } else if (l <= kl && kr <= r) {
            return rmqsum[k] + rmqadd[k] * (kr - kl + 1);
        } else {
            long res = (Math.min(r, kr) - Math.max(l, kl) + 1) * rmqadd[k];
            res += query(l, r, k * 2 + 1, kl, (kl + kr) / 2);
            res += query(l, r, k * 2 + 2, (kl + kr) / 2 + 1, kr);
            return res;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(), Q = in.nextInt();

            long[] array = new long[N];
            for (int i = 0; i < N; i++) {
                array[i] = in.nextLong();
            }

            makeRMQ(array);

            for (int i = 0; i < Q; i++) {
                String c = in.next();
                if (c.equals("Q")) {
                    int l = in.nextInt() - 1, r = in.nextInt() - 1;
                    System.out.println(query(l, r, 0, 0, L - 1));
                } else {
                    int l = in.nextInt() - 1, r = in.nextInt() - 1;
                    long x = in.nextLong();
                    add(l, r, x, 0, 0, L - 1);
                }
            }
        }
    }
}
