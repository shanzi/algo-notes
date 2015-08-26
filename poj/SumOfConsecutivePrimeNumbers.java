import java.io.*;
import java.util.*;

public class SumOfConsecutivePrimeNumbers {
    private static int[] primes(int N) {
        if (N < 2) return new int[0];
        boolean[] isprime = new boolean[N];
        Arrays.fill(isprime, true);
        int count = 1;
        for (int i = 3; i < N; i += 2) {
            if (!isprime[i]) continue;
            for (int j = i; i * j < N; j += 2) isprime[i * j] = false;
            count++;
        }
        int[] res = new int[count];
        int idx = 0;
        res[idx++] = 2;
        for (int i = 3; i < N; i += 2) if (isprime[i]) res[idx++] = i;
        return res;
    }
    private static void solve(int[] primes, int N) {
        int l = 0, r = 0;
        int sum = 0;
        int count = 0;
        while (r < primes.length) {
            sum += primes[r++];
            while (sum > N) sum -= primes[l++];
            if (sum == N) count++;
        }
        System.out.println(count);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] primeNumbers = primes(10000);
        while (true) {
            int N = in.nextInt();
            if (N == 0) break;
            solve(primeNumbers, N);
        }
    }
}
