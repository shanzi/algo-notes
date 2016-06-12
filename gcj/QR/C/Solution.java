import java.io.*;
import java.util.*;

public class Solution {
    private static long[] factors = new long[11];
    private static long findPrime(long N) {
        for(long i = 2; i * i <= N; i++) {
            if ((N % i) == 0) return i;
        }
        return -1;
    }

    private static long toNumberOfBase(long value, int i) {
        long res = 0;
        long c = 1;
        while (value > 0) {
            if ((value & 1) == 1) {
                res += c;
            }
            c *= i;
            value >>= 1;
        }
        return res;
    }

    private static boolean checkCoinJam(long value) {
        for (int i = 2; i <= 10; i++) {
            long n = toNumberOfBase(value, i);
            factors[i] = findPrime(n);
            if (factors[i] < 0) {
                return false;
            }
        }
        return true;
    }

    private static void solve(int N, int J) {
        long value = (((long)1) << (N - 1)) | 1;
        do {
            if (checkCoinJam(value)) {
                System.out.print(Long.toBinaryString(value) + Long.toBinaryString(value));
                for (int i = 2; i <= 10; i++) {
                    System.out.printf(" %d", factors[i]);
                }
                System.out.println();
                J -= 1;
            }
            value += 2;
        } while (J > 0);
    }

    public static void main(String[] args) {
        System.out.printf("Case #%d:\n", 1);
        solve(16, 500);
    }
}
