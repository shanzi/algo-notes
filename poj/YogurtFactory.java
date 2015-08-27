import java.io.*;
import java.util.*;

public class YogurtFactory {
    private static void solve(long[][] weeks, int S) {
        long cost = 0;
        int lw = 0;
        for (int i = 0; i < weeks.length; i++) {
            if ((weeks[lw][0] + S * (i - lw)) > weeks[i][0]) lw = i;
            cost += (weeks[lw][0] + S * (i - lw)) * weeks[i][1];
        }
        System.out.println(cost);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), S = in.nextInt();
        long[][] weeks = new long[N][2];
        for (int i = 0; i < N; i++) {
            weeks[i][0] = in.nextLong();
            weeks[i][1] = in.nextLong();
        }
        solve(weeks, S);
    }
}
