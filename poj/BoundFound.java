import java.io.*;
import java.util.*;

class PartSum implements Comparable<PartSum>{
    long x;
    long y;
    public PartSum(long x, long y) {
        this.x = x;
        this.y = y;
    }
    public int hashCode() {
        return (int)(x * 10009 + y);
    }
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof PartSum)) return false;
        if (obj == this) return true;
        PartSum a = this;
        PartSum b = (PartSum)obj;
        return a.x == b.x && a.y == b.y;
    }
    public int compareTo(PartSum that) {
        PartSum a = this;
        PartSum b = that;
        return (int)((a.x == b.x) ? a.y - b.y : a.x - b.x);
    }
}

public class BoundFound {
    private static void solve(PartSum[] array, long q) {
        Arrays.sort(array);
        long min = Long.MAX_VALUE;
        long minsum = 0;
        long ml = 0, mu = 0;
        int l = 0;
        for (int u = 0; u < array.length; u++) {
            long sum = array[u].x;
            while (l < u) {
                long diff = sum - array[l].x - q;
                if (Math.abs(diff) < min) {
                    min = Math.abs(diff);
                    ml = array[l].y;
                    mu = array[u].y;
                    minsum = sum - array[l].x;
                }

                if (diff < 0) break;
                l++;
            }
        }
        System.out.printf("%d %d %d\n", minsum, Math.min(ml, mu), Math.max(ml, mu) - 1);
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (true) {
            int N = in.nextInt(), K = in.nextInt();
            if (N == 0 && K == 0) return;
            PartSum[] array = new PartSum[N + 1];
            long sum = 0;
            for (int i = 1; i <= N; i++) {
                array[i - 1] = new PartSum(sum, i);
                sum += in.nextLong();
            }
            array[array.length - 1] = new PartSum(sum, N + 1);
            for (int i = 0; i < K; i++) {
                solve(array, in.nextLong());
            }
        }
    }
}
