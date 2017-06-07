import java.io.*;
import java.util.*;

public class WhoGetsTheCatch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), X = in.nextInt();
        int[] xs = new int[N];
        for (int i = 0; i < N; i++) {
            xs[i] = in.nextInt();
        }
        int[] vs = new int[N];
        for (int i = 0; i < N; i++) {
            vs[i] = in.nextInt();
        }

        int md = 1000000;
        int mv = 0;

        for (int i = 0; i < N; i++) {
            int d = Math.abs(xs[i] - X);
            int v = vs[i];
            if (v * md > mv * d) {
                md = d;
                mv = v;
            }
        }

        int count = 0;
        int idx = -1;
        for (int i = 0; i < N; i++) {
            int d = Math.abs(xs[i] - X);
            int v = vs[i];
            if (v * md == mv * d) {
                count++;
                idx = i;
            }
        }
        if (count == 1) {
            System.out.println(idx);
        } else {
            System.out.println(-1);
        }
    }
}
