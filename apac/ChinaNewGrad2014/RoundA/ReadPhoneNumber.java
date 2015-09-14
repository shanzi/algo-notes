import java.io.*;
import java.util.*;

public class ReadPhoneNumber {
    static private String[] multiple = {
        "double", "triple", "quadruple", "quintuple", "sextuple", "septuple", "octuple", "nonuple", "decuple"
    };
    static private String[] number = {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };
    private static void read(char[] digits, int l, int r, StringBuilder res) {
        int i = l;
        while (i < r) {
            char c = digits[i];
            int count = 1;
            while (++i < r && digits[i] == c) {
                count++;
            }

            if (count < 2 || count > 10) {
                while (count-- > 0) {
                    res.append(' ');
                    res.append(number[c - '0']);
                }
            } else {
                res.append(' ');
                res.append(multiple[count - 2]);
                res.append(' ');
                res.append(number[c - '0']);
            }
        }
    }
    private static String solve(String number, String separate) {
        char[] digits = number.toCharArray();
        String[] seps = separate.split("-");
        int[] sepn = new int[seps.length];
        for (int i = 0; i < sepn.length; i++) sepn[i] = Integer.parseInt(seps[i]);

        StringBuilder res = new StringBuilder();
        int l = 0;
        for (int w : sepn) {
            read(digits, l, l + w, res);
            l += w;
        }
        return res.toString();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            String number = in.next();
            String separate = in.next();
            System.out.printf("Case #%d:%s\n", t + 1, solve(number, separate));
        }
    }
}
