import java.io.*;
import java.util.*;

public class RaisingModuloNumbers {
    private static long pow(long a, long b, long m) {
        long r = 1;
        while (b > 0) {
            if ((b & 1) == 1) r = (r * a) % m;
            a = (a * a) % m;
            b >>= 1;
        }
        return r;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int Z = in.nextInt();

        for (int i = 0; i < Z; i++) {
            int M = in.nextInt(), H = in.nextInt();
            long r = 0;
            for (int j = 0; j < H; j++) {
                int a = in.nextInt(), b = in.nextInt();
                r = (r + pow(a, b, M)) % M;
            }
            System.out.println(r);
        }
    }
}
