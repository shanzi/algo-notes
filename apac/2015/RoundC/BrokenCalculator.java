import java.io.*;
import java.util.*;

public class BrokenCalculator {
    static private int[] C = new int[1000010];
    private static int countForNum(int num, int bit) {
        int count = 0;
        do {
            if (((bit >> (num % 10)) & 1) == 0) return Integer.MAX_VALUE;
            count++;
            num /= 10;
        } while (num > 0);
        return count;
    }
    private static String solve(int N, int bit) {
        for (int i = 0; i <= N; i++) {
            C[i] = countForNum(i, bit);
        }

        for (int i = 1; i < N; i++) {
            if (C[i] == Integer.MAX_VALUE) continue;

            for (int j = 2; (i * (long)j) <= N; j++) {
                if (C[j] == Integer.MAX_VALUE) continue;
                C[i * j] = Math.min(C[i * j], C[i] + C[j] + 1);
            }
        }

        if (C[N] == Integer.MAX_VALUE) return "Impossible";
        else return String.valueOf(C[N] + 1);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int bit = 0;
            for (int i = 0; i < 10; i++) {
                bit |= (in.nextInt() << i);
            }
            int N = in.nextInt();
            System.out.printf("Case #%d: %s\n", t + 1, solve(N, bit));
        }
    }
}
