import java.io.*;
import java.util.*;

public class Blocks {
    private static int MOD = 10007;
    private static int[][] multiple(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < B[0].length; k++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private static int[][] pow(int[][] A, int n) {
        int[][] I = new int[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            I[i][i] = 1;
        }

        while (n > 0) {
            if ((n & 1) == 1) I = multiple(I, A);
            A = multiple(A, A);
            n >>= 1;
        }
        return I;
    }

    private static void solve(int n) {
        int[][] A = {
            {2, 1, 0},
            {2, 2, 2},
            {0, 1, 2}
        };

        A = pow(A, n);
        System.out.println(A[0][0]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNextInt()) {
            int N = in.nextInt();
            
            for (int i = 0; i < N; i++) {
                int n = in.nextInt();
                solve(n);
            }
        }
    }
}
