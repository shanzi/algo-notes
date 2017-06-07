import java.io.*;
import java.util.*;

public class SamsNumber {
    static long MOD = 1000000009;

    private static void printMat(long[][]mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.printf("%5d", mat[i][j]);
            }
            System.out.println();
        }
    }

    private static void mulMat(long[][] A, long[][] B, long[][] C) {
        int M = A.length;
        int N = B.length;
        int L = B[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < L; j++) {
                C[i][j] = 0;
                for (int k = 0; k < N; k++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
    }

    private static long[][] powMat(long[][] A, long n) {
        // A, B, C must be square matrix.
        long[][] B = new long[A.length][A.length];
        long[][] C = new long[A.length][B.length];
        long[][] tmp;
        // C will be initialized as identity matrix
        for (int i = 0; i < C.length; i++) {
            C[i][i] = 1;
        }

        while (n > 0) {
            if ((n & 1) == 1) {
                mulMat(C, A, B);

                // Exchange C and B
                tmp = C;
                C = B;
                B = tmp;
            }

            n >>= 1;
            mulMat(A, A, B);

            // Exchange A and B
            tmp = A;
            A = B;
            B = tmp;
        }

        return C;
    }

    private static long[][] solve(int m, int d) {
        long[][] ans  = new long[m + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            ans[i][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m && j < i; j++) {
                for (int k = Math.max(1, j - d); k <= Math.min(m, j + d); k++) {
                    ans[i][j] = (ans[i][j] + ans[i - j][k]) % MOD;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long s = in.nextLong();
        int m = in.nextInt(), d = in.nextInt();


        long[][] small = solve(m, d);

        if (s <= m) {
            long ans = 0;
            for (int i = 1; i <= m; i++) {
                ans = (ans + small[(int)s][i]) % MOD;
            }
            System.out.println(ans);
            return;
        }

        int M = m * m;
        long[][] mat = new long[M][M];

        for (int j = 0; j < M - m; j++) {
            mat[j + m][j] = 1;
        }

        for (int j = M - m; j < M; j++) {
            int s1 = j / m + 1;
            int d1 = j % m + 1;

            for (int i = 0; i < M; i++) {
                int s2 = i / m;
                int d2 = i % m + 1;
                
                if (Math.abs(d1 - d2) <= d && s2 + d1 == s1) {
                    mat[i][j] = 1;
                }
            }
        }

        mat = powMat(mat, s - m);

        // printMat(mat);

        long res = 0;
        for (int j = M - m; j < M; j++) {
            for (int i = 0; i < M; i++) {
                int a = i / m + 1;
                int b = i % m + 1;
                res = (res + small[a][b] * mat[i][j]) % MOD;
            }
        }

        System.out.println(res);
    }
}
