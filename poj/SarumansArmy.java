import java.io.*;
import java.util.*;

public class SarumansArmy {
    private static void solve(int[] army, int R) {
        int count = 0;
        for (int i = 0; i < army.length;) {
            int a = army[i];
            while (i < army.length && army[i] <= a + R) i++;
            count++;
            int b = army[i - 1];
            while (i < army.length && army[i] <= b + R) i++;
        }
        System.out.println(count);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int R = in.nextInt(), N = in.nextInt();
            if (R < 0) return;
            int[] army = new int[N];
            for (int i = 0; i < N; i++) {
                army[i] = in.nextInt();
            }
            Arrays.sort(army);
            solve(army, R);
        }
    }
}
