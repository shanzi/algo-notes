import java.util.*;

public class PairsOfStrings {
    final static long MOD = 1000000007;
    private long pow(long n, long k) {
        long res = 1;

        while (k > 0) {
            if ((k & 1) == 1) res = (res * n) % MOD;
            n = (n * n) % MOD;
            k >>= 1;
        }

        return res;
    }

    private int getDivisors(long n, long[] array) {
        int p = 0;
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                array[p++] = i;
                if (n / i != i)
                    array[p++] = n / i;
            }
        }
        return p;
    }

    private long modadd(long a, long b) {
        a = (a + b) % MOD;
        if (a < 0) a += MOD;
        return a;
    }

    public int getNumber(int n, int k) {
        long[] num = new long[1500];
        long[] divisors = new long[1500];
        long count = 0;

        int len = getDivisors(n, divisors);

        Arrays.sort(divisors, 0, len);

        for (int i = 0; i < len; i++) {
            long a = divisors[i];
            num[i] = pow(k, a);

            for (int j = 0; j < i; j++) {
                long b = divisors[j];
                if (b < a && a % b == 0) {
                    num[i] = modadd(num[i], -num[j]);
                }
            }

            count = modadd(count, num[i] * a % MOD);
        }

        return (int)count;
    }
}
