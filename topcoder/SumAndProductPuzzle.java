import java.util.*;

public class SumAndProductPuzzle {
    public long getSum(int A, int B) {
        boolean[] prime = new boolean[B + 1];
        boolean[] good = new boolean[B + 1];
        Arrays.fill(prime, true);
        Arrays.fill(good, true);
        prime[0] = false;
        prime[1] = false;

        for (int x = 2; x * x <= B; x++) {
            if (prime[x]) {
                for (int y = x * x; y <= B; y+= x) {
                    prime[y] = false;
                }
            }
        }

        for (int x = 2; x * x + 1 <= B; x++) {
            for (int y = x; x * y + 1 <= B; y++) {
                if (!prime[x + y - 1]) {
                    good[x * y + 1] = false;
                }
            }
        }

        for (int i = A; i <= B; i++) {
            if (prime[i - 1]) {
                good[i] = false;
            }
        }

        good[0] = false;
        good[1] = false;
        good[2] = false;
        long res = 0;
        for (int i = A; i <= B; i++) {
            if (good[i]) res += i;
        }
        return res;
    }
}
