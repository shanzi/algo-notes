import java.io.*;
import java.util.*;

public class MthMatrix {
    private static long f(long i, long j) {
        return i * i + 100000 * i + j * j - 100000 * j + i * j;
    }
    private static boolean isLess(long N, long M, long mid) {
        long count = 0;
        for (int j = 1; j <= N; j++) {
            long l = 1, u = N;
            while (l <= u) {
                long i = (l + u) / 2;
                if (f(i, j) < mid) l = i + 1;
                else u = i - 1;
            }
            count += u;
        }
        return count < M;
    }
    private static void solve(long N, long M) {
        long l = -100000 * N, u = N * N + 100000 * N + N * N + N * N;
        while (l <= u) {
            long mid = (l + u) / 2;
            if (isLess(N, M, mid)) l = mid + 1;
            else u = mid - 1;
        }
        System.out.println(u);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            solve(in.nextLong(), in.nextLong());
        }
    }
}
