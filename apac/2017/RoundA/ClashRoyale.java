import java.io.*;
import java.util.*;

class Case implements Comparable<Case> {
    long cost;
    long power;
    Case(long cost, long power) {
        this.cost = cost;
        this.power = power;
    }

    public int compareTo(Case that) {
        if (this.cost < that.cost) return -1;
        else if (this.cost > that.cost) return 1;
        else {
            if (this.power < that.power) return 1;
            else if (this.power > that.power) return -1;
            else return 0;
        }
    }
}

public class ClashRoyale {
    static int N;
    static long M;
    static int[] ID = new int[8];
    static int[] K = new int[15];
    static int[] L = new int[15];
    static long[][] A = new long[15][15];
    static long[][] C = new long[15][15];

    static int popcount(int v) {
        int count = 0;
        while (v != 0) {
            count++;
            v -= v & -v;
        }
        return count;
    }

    static long solveSub(int mask) {
        int a = 0;
        for (int i = 0; i < 15; i++) {
            if (((mask >> i) & 1) == 1) {
                ID[a++] = i;
            }
        }

        List<Case> left = new ArrayList<Case>();
        List<Case> right = new ArrayList<Case>();

        for (int i = L[ID[0]]; i <= K[ID[0]]; i++) {
            for (int j = L[ID[1]]; j <= K[ID[1]]; j++) {
                for (int k = L[ID[2]]; k <= K[ID[2]]; k++) {
                    for (int l = L[ID[3]]; l <= K[ID[3]]; l++) {
                        left.add(new Case(
                                    C[ID[0]][i] +
                                    C[ID[1]][j] +
                                    C[ID[2]][k] +
                                    C[ID[3]][l],
                                    A[ID[0]][i] +
                                    A[ID[1]][j] +
                                    A[ID[2]][k] +
                                    A[ID[3]][l]
                                    ));
                    }
                }
            }
        }

        for (int i = L[ID[4]]; i <= K[ID[4]]; i++) {
            for (int j = L[ID[5]]; j <= K[ID[5]]; j++) {
                for (int k = L[ID[6]]; k <= K[ID[6]]; k++) {
                    for (int l = L[ID[7]]; l <= K[ID[7]]; l++) {
                        right.add(new Case(
                                    C[ID[4]][i] +
                                    C[ID[5]][j] +
                                    C[ID[6]][k] +
                                    C[ID[7]][l],
                                    A[ID[4]][i] +
                                    A[ID[5]][j] +
                                    A[ID[6]][k] +
                                    A[ID[7]][l]
                                    ));
                    }
                }
            }
        }

        Collections.sort(left);
        Collections.sort(right);


        int top = 0;
        for (int i = 0; i < left.size(); i++) {
            if (left.get(top).power < left.get(i).power) {
                left.set(++top, left.get(i));
            }
        }
        int ltop = top;

        top = 0;
        for (int i = 0; i < right.size(); i++) {
            if (right.get(top).power < right.get(i).power) {
                right.set(++top, right.get(i));
            }
        }
        int rtop = top;

        long res = 0;
        int r = 0;
        for (int l = ltop; l >= 0; l--) {
            while (r < rtop && left.get(l).cost + right.get(r + 1).cost <= M) {
                r++;
            }
            if (left.get(l).cost + right.get(r).cost <= M) {
                res = Math.max(res, left.get(l).power + right.get(r).power);
            }
        }
        return res;
    }

    static long solve() {
        long res = 0;
        for (int i = 0; i < (1 << N); i++) {
            if (popcount(i) == 8) {
                res = Math.max(res, solveSub(i));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            M = in.nextLong();
            N = in.nextInt();
            for (int i = 0; i < N; i++) {
                K[i] = in.nextInt();
                L[i] = in.nextInt();

                for (int j = 1; j <= K[i]; j++) {
                    A[i][j] = in.nextLong();
                }
                for (int j = 2; j <= K[i]; j++) {
                    C[i][j] = in.nextLong();
                }

                for (int j = 1; j <= K[i]; j++) {
                    if (j <= L[i]) C[i][j] = 0;
                    else C[i][j] += C[i][j - 1];
                }
            }
            System.out.printf("Case #%d: %d\n", t + 1, solve());
        }
    }
}
