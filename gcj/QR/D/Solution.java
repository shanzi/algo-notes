import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int K = in.nextInt(), C = in.nextInt(), S = in.nextInt();
            System.out.printf("Case #%d:", t + 1);
            if (C * S < K) System.out.println(" IMPOSSIBLE");
            else {
                int offset = 0;
                HashSet<Long> set = new HashSet<Long>(K);
                for (int i = 0; i < S; i++) {
                    long cur = 0;
                    for (int j = 0; j < C; j++) {
                        cur = cur * K + offset;
                        offset = (offset + 1) % K;
                    }

                    if (!set.contains(cur)) {
                        System.out.printf(" %d", cur + 1);
                        set.add(cur);
                    }
                }
                System.out.println();
            }
        }
    }
}
