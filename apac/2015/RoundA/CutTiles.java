import java.io.*;
import java.util.*;

public class CutTiles {
    private static boolean cutTiles(int[] count, int i, long W, long H) {
        long h = Math.max(W, H);
        long w = Math.min(W, H);
        if (w < 1) return false;
        for (int j = i; j >= 0; j--) {
            if (count[j] == 0) continue;
            long subw = ((long)1) << j;
            if (w < subw) continue;
            int cost = (int)Math.min(count[j], h / subw);
            count[j] -= cost;
            cutTiles(count, j, h - cost * subw, subw);
            cutTiles(count, j, w - subw, h);
            return true;
        }
        return false;
    }
    private static void solve(int[] count, long M) {
        int num = 0;
        while(true) {
            if (!cutTiles(count, 32, M, M)) break;
            num++;
        }
        System.out.println(num);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            long M = in.nextLong();
            int[] count = new int[33];
            for (int i = 0; i < N; i++) {
                count[in.nextInt()]++;
            }
            System.out.printf("Case #%d: ", t + 1);
            solve(count, M);
        }
    }
}
