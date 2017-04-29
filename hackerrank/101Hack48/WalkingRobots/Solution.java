import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int q = 0; q < N; q++) {
            String s = in.next();
            int l = 0;
            int r = s.length();

            while (l < s.length() && s.charAt(l) == 'l') l++;
            while (r > 0  && s.charAt(r - 1) == 'r') r--;

            int sum = 0;
            for (int i = l; i < r; i++) {
                if (s.charAt(i) != 'd') sum++;
            }
            System.out.println(sum);
        }
    }
}
