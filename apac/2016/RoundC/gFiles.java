import java.io.*;
import java.util.*;

public class gFiles {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            long left = 0, right = Long.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                long P = in.nextLong(), K = in.nextLong();
                if (P > 0) right = Math.min(right, K * 100 / P);
                if (P < 100) {
                    left = Math.max(left, K * 100 / (P + 1));
                } else {
                    left = K - 1;
                    right = K;
                }
            }
            System.out.printf("Case #%d: ", t + 1);
            if (left + 1 != right) System.out.println(-1);
            else System.out.println(right);
        }
    }
}
