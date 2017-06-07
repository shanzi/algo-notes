import java.io.*;
import java.util.*;

public class NumericNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        int k = in.nextInt(), b = in.nextInt(), m = in.nextInt();
        long sum = 0;

        int ksum = 0;
        int bk = 1;
        for (int i = 0; i < s.length(); i++) {
            if (i >= k) {
                ksum = (ksum - (s.charAt(i - k) - '0') * bk % m + m) % m;
            }

            ksum = (ksum * b + (s.charAt(i) - '0')) % m;

            if (i >= k - 1) {
                sum += ksum;
            } else {
                bk = bk * b % m;
            }
        }
        System.out.println(sum);
    }
}
