import java.io.*;
import java.util.*;

public class Subsequence {
    private static void solve(int[] array, int S) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int l = 0, r = 0;
        while (r < array.length) {
            sum += array[r++];
            while (sum >= S) {
                min = Math.min(min, r - l);
                sum -= array[l++];
            }
        }
        if (min > array.length) System.out.println(0);
        else System.out.println(min);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), S = in.nextInt();
            int[] array = new int[N];
            for (int i = 0; i < N; i++) {
                array[i] = in.nextInt();
            }
            solve(array, S);
        }
    }
}
