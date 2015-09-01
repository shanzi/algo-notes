import java.io.*;
import java.util.*;

class Interval implements Comparable<Interval> {
    int start;
    int end;
    int product;
    public Interval(int x, int y, int z) {
        this.start = x;
        this.end = y;
        this.product = z;
    }
    public int compareTo(Interval that) {
        return this.end - that.end;
    }
}

class RMQ {
    private int L;
    private long[] array;
    public RMQ(long[] a) { 
        L = 1;
        while (L < a.length) L <<= 1;
        array = new long[L * 2 - 1];
        for (int i = 0; i < array.length; i++) {
            array[i + L - 1] = a[i];
        }
        for (int i = L - 2; i >= 0; i--) {
            array[i] = Math.max(array[i * 2 + 1], array[i * 2 + 2]);
        }
    }
    public RMQ(int length) {
        L = 1;
        while (L < length) L <<= 1;
        array = new long[L * 2 - 1];
    }
    public void set(int i, long v) {
        set(i, v, 0, 0, L - 1);
    }
    public long query(int l, int r) {
        return query(l, r, 0, 0, L - 1);
    }
    private void set(int i, long v, int k, int kl, int kr) {
        if (kl <= i && kr >= i) {
            array[k] = Math.max(array[k], v);
            if (kl >= kr) return;
            int mid = (kl + kr) / 2;
            set(i, v, k * 2 + 1, kl, mid);
            set(i, v, k * 2 + 2, mid + 1, kr);
        }
    }
    private long query(int l, int r, int k, int kl, int kr) {
        if (l > kr || r < kl) return Integer.MIN_VALUE;
        else if (l <= kl && kr <= r) return array[k];
        else {
            int mid = (kl + kr) / 2;
            return Math.max(query(l, r, k * 2 + 1, kl, mid), query(l, r, k * 2 + 2, mid + 1, kr));
        }
    }
}

public class MilkingTime {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), M = in.nextInt(), R = in.nextInt();

        Interval[] intervals = new Interval[M];

        for (int m = 0; m < M; m++) {
            int s = in.nextInt(), e = in.nextInt() + R, p = in.nextInt();
            intervals[m] = new Interval(s, e, p);
        }

        Arrays.sort(intervals);
        RMQ dp = new RMQ(N + R + 1);

        for (int i = 0; i < M; i++) {
            Interval interval = intervals[i];
            long max = dp.query(0, interval.start);
            dp.set(interval.end, max + interval.product);
        }

        System.out.println(dp.query(0, N + R + 1));
    }
}
