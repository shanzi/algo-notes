import java.io.*;
import java.util.*;

public class gNumbers {
    static private ArrayList<Long> factors = new ArrayList<Long>(1<<20);
    private static void getFactors(long N) {
        factors.clear();
        for (long i = 2; i * i <= N; i++) {
            if (N % i == 0) {
                factors.add(i);
                while (N % i == 0) N /= i;
            }
        }
        if (N > 1) factors.add(N);
    }
    private static boolean isPrime(long N) {
        for(long i = 2; i * i <= N; i++) {
            if ((N % i) == 0) return false;
        }
        return true;
    }
    private static boolean isGNumber(long N) {
        long res = 0;
        while (N > 0) {
            res += (N % 10);
            N /= 10;
        }
        return (res == 1 || isPrime(res));
    }
    private static boolean solve(long N, boolean player) {
        if (isGNumber(N)) {
            return !player;
        }
        for (long factor : factors) {
            if (N % factor == 0) {
                long C = N;
                while (C % factor == 0) C /= factor;
                if (solve(C, !player) == player) return player;
            }
        }
        return !player;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            long N = in.nextLong();
            getFactors(N);
            System.out.printf("Case #%d: %s\n", t + 1, solve(N, false) ? "Seymour" : "Laurence");
        }
    }
}
