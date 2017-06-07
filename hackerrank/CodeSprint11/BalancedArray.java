import java.io.*;
import java.util.*;

public class BalancedArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] arr = new int[N];
        int suml = 0;
        int sumr = 0;
        for (int i = 0; i < N / 2; i++) {
            suml += in.nextInt();
        }
        for (int i = 0; i < N / 2; i++) {
            sumr += in.nextInt();
        }

        System.out.println(Math.abs(suml- sumr));
    }
}
