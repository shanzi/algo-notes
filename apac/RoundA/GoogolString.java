import java.io.*;
import java.util.*;

public class GoogolString {
    private static boolean isone(long l, long k) {
        if (k == l / 2) return false;
        else if (k < l / 2) return isone(l / 2, k);
        else return !isone(l / 2, l - k - 1);
    }
    private static void solve(long K) {
        long l = 0;
        while (l <= K) l = l * 2 + 1;
        System.out.println(isone(l, K) ? "1" : "0");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            System.out.printf("Case #%d: ", t + 1);
            solve(in.nextLong() - 1);
        }
    }
}
