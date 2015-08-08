import java.io.*;
import java.util.*;

public class TrainingLittleCats {
    private static long[][] multiple(long[][] A, long[][] B) {
        long[][] C = new long[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int k = 0; k < B[0].length; k++) {
                if (A[i][k] == 0) continue; // IMPORTANT: optimize time cost!
                for (int j = 0; j < B.length; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]);
                }
            }
        }
        return C;
    }

    private static long[][] pow(long[][] A, long n) {
        long[][] I = new long[A.length][A.length];
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

    private static long[][] makeIdentity(int n) {
        long[][] I = new long[n][n];
        for (int i = 0; i < n; i++) {
            I[i][i] = 1;
        }
        return I;
    }

    private static void give(long[][] A, int i) {
        A[i][0]++;
    }

    private static void eat(long[][] A, int i) {
        for (int j = 0; j < A[0].length; j++) {
            A[i][j] = 0;
        }
    }

    private static void swap(long[][] A, int i, int j) {
        long[] temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        boolean flag = false;

        while(in.hasNextInt()) {
            int n = in.nextInt() + 1, m = in.nextInt(), k = in.nextInt();

            if (n == 1) return;

            if (flag) System.out.println();
            else flag = true;

            long[][] A = makeIdentity(n);

            for (int i = 0; i < k; i++) {
                String op = in.next();
                if (op.equals("g")) {
                    give(A, in.nextInt());
                } else if (op.equals("e")) {
                    eat(A, in.nextInt());
                } else {
                    swap(A, in.nextInt(), in.nextInt());
                }
            }

            A = pow(A, m);
            System.out.printf("%d", A[1][0]);
            for (int i = 2; i < n; i++) {
                System.out.printf(" %d", A[i][0]);
            }
        }
    }
}
