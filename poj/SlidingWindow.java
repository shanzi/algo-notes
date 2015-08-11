import java.io.*;
import java.util.*;

public class SlidingWindow {
    private static int[] a;
    private static int[] deq;

    private static void solve(int n, int k) {
        int s = 0, t = 0;

        StringBuffer sbuf = new StringBuffer(n * 5);
        // output minimum values
        for (int i = 0; i < n; i++) {
            while (s < t && deq[s] <= i - k) s++;
            while (s < t && a[deq[t - 1]] >= a[i]) t--;

            deq[t++] = i;

            if (i == k - 1) sbuf.append(a[deq[s]]);
            else if (i >= k) {
                sbuf.append(' ');
                sbuf.append(a[deq[s]]);
            }
        }

        System.out.println(sbuf.toString());
        
        // empty dequeue
        s = 0;
        t = 0;
        sbuf.setLength(0);

        // output maximum values
        for (int i = 0; i < n; i++) {
            while (s < t && deq[s] <= i - k) s++;
            while (s < t && a[deq[t - 1]] <= a[i]) t--;

            deq[t++] = i;

            if (i == k - 1) sbuf.append(a[deq[s]]);
            else if (i >= k) {
                sbuf.append(' ');
                sbuf.append(a[deq[s]]);
            }
        }

        System.out.println(sbuf.toString());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt(), k = in.nextInt();
            a = new int[n];
            deq = new int[n + 5];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            solve(n, k);
        }
    }
}
