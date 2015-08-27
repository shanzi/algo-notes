import java.io.*;
import java.util.*;

class Cow implements Comparable<Cow>{
    long x;
    long y;
    public Cow(long x, long y) {
        this.x = x;
        this.y = y;
    }
    public int compareTo(Cow that) {
        Cow a = this;
        Cow b = that;
        long res = a.x * b.y - a.y * b.x;
        if (res > 0) return 1;
        else if (res < 0) return -1;
        else return 0;
    }
}
public class ProtectingTheFlowers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        Cow[]cows = new Cow[N];
        long sum = 0;
        for (int i = 0; i < N; i++) {
            cows[i] = new Cow(in.nextLong(), in.nextLong());
            sum += cows[i].y;
        }

        Arrays.sort(cows);
        long cost = 0;

        for (int i = 0; i < N; i++) {
            sum -= cows[i].y;
            cost += sum * cows[i].x * 2;
        }
        System.out.println(cost);
    }
}
