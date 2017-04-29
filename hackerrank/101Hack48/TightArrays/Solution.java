import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();

        if (a != b && b != c) {
            int dab = Math.abs(b - a) + 1;
            int dbc = Math.abs(c - b) + 1;

            int res = dab + dbc - 1;
            System.out.println(res);
        } else if (a == b && b == c) {
            System.out.println(1);
        } else {
            System.out.println(Math.abs(c - a) + 1);
        }
    }
}
