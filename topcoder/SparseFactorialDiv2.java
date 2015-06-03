import java.util.*;

public class SparseFactorialDiv2 {
    private long getC(long upper, long divisor, long d) {
        if (upper % divisor >= d) {
            return upper / divisor + 1;
        } else {
            return upper / divisor;
        }
    }

    private long solve(long upper, long divisor) {
        long[] bestK = new long[(int)divisor];
        Arrays.fill(bestK, -1);
        long res = 0;

        for (long i = 0; i * i < upper; i++) {
            int d = (int)(i * i % divisor);
            if (bestK[d] < 0) bestK[d] = i;
        }

        for (int d = 0; d < divisor; d++) {
            if (bestK[d] < 0) continue;
            res += getC(upper, divisor, d) - getC(bestK[d] * bestK[d], divisor, d);
        }
        return res;
    }

    public long getCount(long lo, long hi, long divisor) {
        return solve(hi, divisor) - solve(lo - 1, divisor);
    }
}
