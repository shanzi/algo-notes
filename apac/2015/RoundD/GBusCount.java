import java.io.*;
import java.util.*;

class BIT {
    private int[] array;
    public BIT(int N) { 
        array = new int[N + 1];
    }
    public void add(int i, int n) {
        while (i > 0 && i < array.length) {
            array[i] += n;
            i += i & -i;
        }
    }
    public int query(int i) {
        int ret = 0;
        while (i > 0 && i < array.length) {
            ret += array[i]; 
            i -= i & -i;
        }
        return ret;
    }
    public void clear() {
        Arrays.fill(array, 0);
    }
}

public class GBusCount {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        BIT bit = new BIT(5000);
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            for (int i = 0; i < N; i++) {
                int a = in.nextInt(), b = in.nextInt();
                bit.add(a, 1);
                bit.add(b + 1, -1);
            }
            System.out.printf("Case #%d:", t + 1);
            int Q = in.nextInt();
            for (int i = 0; i < Q; i++) {
                int P = in.nextInt();
                System.out.printf(" %d", bit.query(P));
            }
            System.out.println();
            bit.clear();
        }
    }
}
