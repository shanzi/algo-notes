import java.io.*;
import java.util.*;

public class FourValuesWhoseSumIsZero {
    private static int upperbound(long[] array, long value) {
        int l = 0, r = array.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] <= value) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
    private static int lowerbound(long[] array, long value) {
        int l = 0, r = array.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] >= value) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        str = in.readLine().split(" ");
        int N = Integer.parseInt(str[0]);

        long[] A = new long[N];
        long[] B = new long[N];
        long[] C = new long[N];
        long[] D = new long[N];
        for (int i = 0; i < N; i++) {
            str = in.readLine().split(" ");
            A[i] = Long.parseLong(str[0]);
            B[i] = Long.parseLong(str[1]);
            C[i] = Long.parseLong(str[2]);
            D[i] = Long.parseLong(str[3]);
        }

        long[] CD = new long[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                CD[i * N + j] = C[i] + D[j];
            }
        }
        Arrays.sort(CD);
        long count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long sum = A[i] + B[j];
                count += upperbound(CD, -sum) - lowerbound(CD, -sum) + 1;
            }
        }
        System.out.println(count);
    }
}
