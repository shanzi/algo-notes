import java.io.*;
import java.util.*;

public class GCDLCMInverse {
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        String[] str;

        while ((s = in.readLine()) != null && !s.isEmpty()) {
            str = s.split(" ");
            long M = Long.parseLong(str[0]), N = Long.parseLong(str[1]);

            N /= M;

            long a = 1;

            for (long i = (long)Math.sqrt(N); i >= 1; i--) {
                if (N % i == 0 && gcd(i, N / i) == 1) {
                    a = i;
                    break;
                }
            }
            System.out.printf("%d %d\n", a * M, (N / a) * M);
        }
    }
}
