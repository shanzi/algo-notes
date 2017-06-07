import java.io.*;
import java.util.*;

public class FairCut {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        if (k > (n >> 1)) k = n - k;
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
        }

        Arrays.sort(array);

        long[][] DP = new long[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            DP[i][0] = DP[i - 1][0] - k * array[i - 1];
            for (int j = 1; j <= k; j++) {
                long li = DP[i - 1][j - 1] + ((i - j) * 2 - (n - k)) * array[i - 1];
                long lu = DP[i - 1][j] + (j * 2 - k) * array[i - 1];

                DP[i][j] = Math.min(li, lu);
            }
        }

        System.out.println(DP[n][k]);
    }
}
