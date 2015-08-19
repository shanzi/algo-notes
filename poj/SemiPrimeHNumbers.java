import java.io.*;
import java.util.*;

public class SemiPrimeHNumbers {

    private static int L = 1000001;

    private static int pos(int h) {
        return h / 4;
    }

    private static int[] getSemiPrimes() {
        int N = pos(L) + 1;
        int[] hs = new int[N];
        int count = 0;
        for (int i = 5; i <= L; i += 4) {
            if (hs[pos(i)] > 0) continue;
            count++;
            for (int j = i; (i * (long)j) <= L; j += 4) {
                hs[pos(i * j)]++;
            }
        }
        int[] primes = new int[count];
        int idx = 0;

        for (int i = 1; i < hs.length; i++) {
            if (hs[i] == 0) {
                primes[idx++] = i * 4 + 1;
            }
        }

        HashSet<Integer> pset = new HashSet<Integer>(count);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j <= i; j++) {
                long a = primes[i];
                long b = primes[j];

                if (a * b >= L) break;
                pset.add((int)(a * b));
            }
        }
        int[] res = new int[pset.size()];
        idx = 0;
        for (Integer i : pset) res[idx++] = i;
        Arrays.sort(res);
        return res;
    }

    private static int countNumber(int[] semiprimes, int n) {
        int l = 0;
        int r = semiprimes.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (semiprimes[mid] <= n) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] semiprimes = getSemiPrimes();
        while (in.hasNextInt()) {
            int n = in.nextInt();
            if (n == 0) break;
            System.out.printf("%d %d\n", n, countNumber(semiprimes, n));
        }
    }
}
