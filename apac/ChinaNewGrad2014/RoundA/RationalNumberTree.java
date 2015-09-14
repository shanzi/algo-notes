import java.io.*;
import java.util.*;

class Pair {
    long x;
    long y;
    public Pair(long x, long y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return String.format("%d %d", x, y);
    }
}

public class RationalNumberTree {
    private static Pair findRational(long n) {
        if (n == 1) return new Pair(1, 1);
        else {
            Pair p = findRational(n / 2);
            if (n % 2 == 0) {
                p.y += p.x;
            } else {
                p.x += p.y;
            }
            return p;
        }
    }
    private static long findN(long a, long b) {
        if (a == 1 && b == 1) return 1;
        else if (a < b) {
            return 2 * findN(a, b - a);
        } else {
            return 2 * findN(a - b, b) + 1;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int type = in.nextInt();
            if (type == 1) {
                long n = in.nextLong();
                System.out.printf("Case #%d: %s\n", t + 1, findRational(n));
            } else {
                long p = in.nextLong(), q = in.nextLong();
                System.out.printf("Case #%d: %d\n", t + 1, findN(p, q));
            }
        }
    }
}
