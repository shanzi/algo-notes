import java.io.*;
import java.util.*;

public class XFactorChains {
    private static int[] primes(int N) {
        if (N < 2) return new int[0];
        boolean[] isprime = new boolean[N];
        Arrays.fill(isprime, true);
        int count = 1;
        for (int i = 3; i < N; i += 2) {
            if (!isprime[i]) continue;
            for (int j = i; (i * (long)j) < N; j += 2) isprime[i * j] = false;
            count++;
        }
        int[] res = new int[count];
        int idx = 0;
        res[idx++] = 2;
        for (int i = 3; i < N; i += 2) if (isprime[i]) res[idx++] = i;
        return res;
    }

    private static void solve(int[] primeList, int n) {
        long a = 0;
        long b = 1;
        long c = 0;
        for (int p : primeList) {
            if (n % p != 0) continue;
            while (n % p == 0) {
                n /= p;
                a++;
                c++;
                b *= a;
            }
            while (c > 0) b /= c--;
            if (n == 1) break;
        }

        System.out.printf("%d %d\n", a, b);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] ps = primes(1 << 20);
        while (in.hasNextInt()) {
            solve(ps, in.nextInt());
        }
    }
}
