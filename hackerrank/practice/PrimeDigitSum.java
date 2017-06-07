import java.io.*;
import java.util.*;

public class PrimeDigitSum {
    final static int MAX_N = 400005;

    static int L;
    static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};
    static int[][] prevv = new int[400][15];

    static int MOD = 1000000007;
    static int[] DPL = new int[400];
    static int[] DP = new int[400];
    static int[] RES = new int[MAX_N];

    private static boolean isPrime(int n) {
        return Arrays.binarySearch(primes, n) >= 0;
    }

    private static boolean isGood(int x) {
        int a = x % 10; x /= 10;
        int b = x % 10; x /= 10;
        int c = x % 10; x /= 10;
        int d = x % 10; x /= 10;
        int e = x % 10;

        return (isPrime(a + b + c) &&
                isPrime(b + c + d) &&
                isPrime(c + d + e) &&
                isPrime(a + b + c + d) &&
                isPrime(b + c + d + e) &&
                isPrime(a + b + c + d + e));
    }

    private static void makePrevv() {
        int l = 0;
        for (int i = 0; i < 100000; i++) {
            if (isGood(i)) prevv[l++][0] = i;
        }

        for (int i = 0; i < l; i++) {
            int ll = 1;
            int crt = prevv[i][0];
            for (int j = 0; j < 10; j++) {
                int prev = crt / 10 + j * 10000;
                for (int k = 0; k < l; k++) {
                    if (prevv[k][0] == prev) {
                        prevv[i][ll++] = k;
                        break;
                    }
                }
            }
            prevv[i][ll] = -1;
        }
        L = l;
    }

    private static void solve() {
        makePrevv();
        RES[1] = 9;
        RES[2] = 90;
        RES[3] = 303;
        RES[4] = 280;
        for (int i = 0; i < L; i++) {
            int v = prevv[i][0];

            if (prevv[i][0] >= 10000) {
                DPL[i] = 1;
                RES[5]++;
            }
        }

        for (int i = 6; i < MAX_N; i++) {
            Arrays.fill(DP, 0);
            for (int j = 0; j < L; j++) {
                for (int k = 1; k < 15; k++) {
                    if (prevv[j][k] < 0) break;
                    DP[j] = (DP[j] + DPL[prevv[j][k]]) % MOD;
                }
                RES[i] = (RES[i] + DP[j]) % MOD;
            }

            int[] tmp = DPL;
            DPL = DP;
            DP = tmp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        solve();

        int Q = Integer.valueOf(in.readLine());
        for (int q = 0; q < Q; q++) {
            int n = Integer.valueOf(in.readLine());
            String s = String.valueOf(RES[n]);
            out.write(s, 0, s.length());
            out.newLine();
        }
        out.flush();
    }
}
