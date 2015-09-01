import java.io.*;
import java.util.*;

public class Sumsets {
    static private int MOD = 1000000000;
    static private int[] array = new int[1000010];
    private static void solve(int N) {
        for (int i = 0; i <= N; i++) array[i] = 1;
        for (int i = 2; i <= N; i <<= 1) {
            for (int j = i; j <= N; j++) {
                array[j] = (array[j] + array[j - i]) % MOD;
            }
        }
        System.out.println(array[N]);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        solve(N);
    }
}
