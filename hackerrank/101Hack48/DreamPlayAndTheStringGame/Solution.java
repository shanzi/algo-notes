import java.io.*;
import java.util.*;

public class Solution {
    private static boolean AmandaWin(String S, String P) {
        if (S.equals(P)) return true;
        if (S.length() < P.length()) return false;
        return !StevenWin(S.substring(1), P) || !StevenWin(S.substring(0, S.length() - 1), P);
    }

    private static boolean StevenWin(String S, String P) {
        if (S.equals(P)) return false;
        if (S.length() < P.length()) return true;

        int dl = S.length() - P.length();
        if (dl > 2 && (dl & 1) == 0) {
            return StevenWin(S.substring(dl / 2 - 1, dl / 2 + 1 + P.length()), P);
        }

        return !AmandaWin(S.substring(1), P) || !AmandaWin(S.substring(0, S.length() - 1), P);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String S = in.next();
            String P = in.next();
            
            System.out.println(StevenWin(S, P) ? "Steven" : "Amanda");
        }
    }
}

