import java.io.*;
import java.util.*;

public class BestCowLine {
    private static boolean compare(char[] str, int l, int r) {
        for (int i = 0; i < r - l + 1; i++) {
            if (str[l + i] < str[r - i]) return true;
            else if (str[l + i] > str[r - i]) return false;
        }
        return true;
    }
    private static void solve(char[] str) {
        int l = 0, r = str.length - 1;
        for (int i = 0; i < str.length; i++) {
            if (compare(str, l, r)) {
                System.out.print(str[l++]);
            } else {
                System.out.print(str[r--]);
            }
            if ((i % 80) == 79) System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        char[] str = new char[N];
        for (int i = 0; i < N; i++) {
            str[i] = in.next().charAt(0);
        }

        solve(str);
    }
}
