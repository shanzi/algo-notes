import java.io.*;
import java.util.*;

public class gNumbers {
    private static int[] primes;
    private static int[] getPrimes(int N) {
        if (N < 2) return new int[0];
        boolean[] isprime = new boolean[N];
        Arrays.fill(isprime, true);
        int count = 1;
        for (int i = 3; i < N; i += 2) {
            if (!isprime[i]) continue;
            for (int j = i; i * (long)j < N; j += 2) isprime[i * j] = false;
            count++;
        }
        int[] res = new int[count];
        int idx = 0;
        res[idx++] = 2;
        for (int i = 3; i < N; i += 2) if (isprime[i]) res[idx++] = i;
        return res;
    }
    private static int getGNumber(long N) {
        long res = 0;
        while (N > 0) {
            res += (N % 10);
            N /= 10;
        }
        return (int)res;
    }
    private static boolean solve(long N, boolean player) {
        int gnum = getGNumber(N);
        if (gnum == 1 || Arrays.binarySearch(primes, gnum) >= 0) {
            return !player;
        }
        for (int i = primes.length - 1; i >= 0; i--) {
            if (N % primes[i] == 0) {
                long C = N;
                while (C % primes[i] == 0) C /= primes[i];
                if (solve(C, !player) == player) return player;
            }
        }
        return !player;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        primes = getPrimes(100000000);
        for (int t = 0; t < T; t++) {
            long N = in.nextLong();
            System.out.printf("Case #%d: %s\n", t + 1, solve(N, false) ? "Seymour" : "Laurence");
        }
    }
}
