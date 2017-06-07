import java.io.*;
import java.util.*;

public class Abbreviation {
    private static boolean solve(String a, String b) {
        boolean[][] DP = new boolean[b.length() + 1][a.length() + 1];
        DP[0][0] = true;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) >= 'a' && a.charAt(i) <= 'z') {
                DP[0][i + 1] = true;
            } else {
                break;
            }
        }

        for (int i = 0; i < b.length(); i++) {
            char ch = b.charAt(i);
            for (int j = 0; j < a.length(); j++) {
                char ch2 = a.charAt(j);
                if (ch2 >= 'A' && ch2 <= 'Z') {
                    DP[i + 1][j + 1] = DP[i][j] && (ch2 == ch);
                } else {
                    DP[i + 1][j + 1] = (
                            DP[i + 1][j] ||
                            (DP[i][j] && (ch - 'A') == (ch2 - 'a')));
                }
            }
        }
        return DP[b.length()][a.length()];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String a = in.next();
            String b = in.next();

            System.out.println(solve(a, b) ? "YES" : "NO");
        }
    }
}
