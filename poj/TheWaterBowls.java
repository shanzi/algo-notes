import java.io.*;
import java.util.*;

public class TheWaterBowls {
    private static int solve(int[]bowls, int first) {
        bowls[0] = first;
        int[] flip = new int[22];

        int sumk = 0;
        int count = 0;
        for (int i = 0; i < 20; i++) {
            if (i >= 3) sumk -= flip[i - 3];
            if ((sumk + bowls[i]) % 2 == 1) {
                flip[i]++;
                count++;
            }
            sumk += flip[i];
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] bowls = new int[22];
        for (int i = 1; i <= 20; i++) bowls[i] = in.nextInt();
        System.out.println(Math.min(solve(bowls, 0), solve(bowls, 1)));
    }
}
