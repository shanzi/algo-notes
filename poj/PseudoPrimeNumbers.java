import java.io.*;
import java.util.*;

public class PseudoPrimeNumbers {
    private static long power(long a, long b) {
        long r = 1;
        long mod = b;
        while (b > 0) {
            if ((b & 1) == 1) r = (r * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return r;
    }
    private static boolean isPrime(long N) {
        for(long i = 2; i * i <= N; i++) {
            if ((N % i) == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            long p = in.nextLong(), a = in.nextLong();
            if (a == 0 && p == 0) return;

            System.out.println((power(a, p) == a && !isPrime(p)) ? "yes" : "no");
        }
    }
}
