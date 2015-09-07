import java.io.*;
import java.util.*;

public class SevenSegmentDisplay {
    static private int[] code = {
        0b1111110, 0b0110000, 0b1101101, 0b1111001, 0b0110011, 0b1011011,
        0b1011111, 0b1110000, 0b1111111, 0b1111011
    };
    private static int check(int[] array, int badbits, int n) {
        for (int i = 1; i <= array.length; i++) {
            int c = code[(n + i) % 10];
            int v = array[array.length - i];
            if ((c & ~badbits) != v) return -1;
        }

        return code[n] & ~badbits;
    }
    private static void solve(int[] array) {
        int goodbits = 0;
        for(int i : array) goodbits |= i;
        int sup = ~goodbits & 0b1111111;
        int sub = sup;
        HashSet<Integer> set = new HashSet<Integer>();
        do {
            for (int i = 0; i < 10; i++) {
                int r = check(array, sub, i);
                if (r >= 0) set.add(r);
            }
            sub = (sub - 1) & sup;
        } while (sub != sup);

        if (set.size() != 1) System.out.println("ERROR!");
        else {
            for (int res : set) {
                System.out.println(String.format("%7s", Integer.toBinaryString(res)).replace(' ', '0'));
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            int[] array = new int[N];
            for (int i = 0; i < N; i++) {
                array[i] = Integer.parseInt(in.next(), 2);
            }
            System.out.printf("Case #%d: ", t + 1);
            solve(array);
        }
    }
}
