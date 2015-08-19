import java.io.*;
import java.util.*;

public class DeadFraction {
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        String s;
        while ((s = in.readLine()) != null && !s.isEmpty()) {
            if (s.equals("0")) break;

            str = s.split("\\.");
            String digits = str[1];
            long n = Long.parseLong(digits);
            int L = 1;
            for(int i = 0; i < digits.length(); i++) L *= 10;

            long a = 0, b = Long.MAX_VALUE;

            for (int c = 10; c <= L; c *= 10) {
                long u = n - n / c;
                long d = (L / c) * (c - 1);

                long g = gcd(u, d);

                if (d / g < b) {
                    a = u / g;
                    b = d / g;
                } 
            }

            System.out.printf("%d/%d\n", a, b);
        }

    }
}
