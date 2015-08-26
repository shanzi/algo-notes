import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair>{
    long x;
    long y;
    public Pair(long x, long y) {
        this.x = x;
        this.y = y;
    }
    public int hashCode() {
        return (int)(x * 10009 + y);
    }
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Pair)) return false;
        if (obj == this) return true;
        Pair a = this;
        Pair b = (Pair)obj;
        return a.x == b.x && a.y == b.y;
    }
    public int compareTo(Pair that) {
        Pair a = this;
        Pair b = that;
        return (int)((a.x == b.x) ? a.y - b.y : a.x - b.x);
    }
}

public class GraveyardDesign {
    private static void solve(long N) {
        long sum = 0;
        ArrayList<Pair> result = new ArrayList<Pair>();
        long l = 1;
        for (long i = 1; i * i <= N; i++) {
            sum += i * i;
            while (sum > N) {
                sum -= l * l;
                l++;
            }
            if (sum == N) result.add(new Pair(i - l + 1, l));
        }
        System.out.println(result.size());
        Collections.sort(result);
        for (int i = result.size() - 1; i >= 0; i--) {
            Pair p = result.get(i);
            System.out.printf("%d", p.x);
            for (int t = 0; t < p.x; t++) {
                System.out.printf(" %d", p.y + t);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long N = in.nextLong();
        solve(N);
    }
}
