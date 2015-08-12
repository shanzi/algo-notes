/*
 * WARNING: This solution has not been accepted on POJ
 */

import java.io.*;
import java.util.*;


public class Constellations {
    private static long B1 = 9973;
    private static long B2 = 100000007;

    private static long patternHash(int[][] pattern) {
        long hash = 0;
        for (int i = 0; i < pattern.length; i++) {
            long linehash = 0;
            for (int j = 0; j < pattern[0].length; j++) {
                linehash = linehash * B1 + pattern[i][j];
            }
            hash = hash * B2 + linehash;
        }
        return hash;
    }

    private static long[][] skyHash(int[][] sky, int P, int Q) {
        long[][] hash = new long[sky.length - P + 1][sky[0].length - Q + 1];
        long[] colhash = new long[sky.length];
        
        long lq = 1;
        long lp = 1;
        for (int i = 0; i < Q; i++) lq *= B1;
        for (int i = 0; i < P; i++) lp *= B2;

        for (int i = 0; i < sky.length; i++) {
            for (int j = 0; j < Q; j++) {
                colhash[i] = colhash[i] * B1 + sky[i][j];
            }
        }

        long rowhash = 0;
        for (int i = 0; i < sky.length; i++) {
            rowhash = rowhash * B2 + colhash[i];

            if (i >= P) {
                rowhash = rowhash - colhash[i - P] * lp;
            }

            if (i >= P - 1) {
                hash[i - P + 1][0] = rowhash;
            }
        }

        for (int j = Q; j < sky[0].length; j++) {
            for (int i = 0; i < sky.length; i++) {
                colhash[i] = colhash[i] * B1 + sky[i][j] - sky[i][j - Q] * lq;
            }

            rowhash = 0;

            for (int i = 0; i < sky.length; i++) {
                rowhash = rowhash * B2 + colhash[i];

                if (i >= P) {
                    rowhash = rowhash - colhash[i - P] * lp;
                }

                if (i >= P - 1) {
                    hash[i - P + 1][j - Q + 1] = rowhash;
                }
            }
        }

        return hash;
    }

    private static void inspect(long[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(" ");
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = 1;
        while (in.hasNextInt()) {
            int N = in.nextInt();
            int M = in.nextInt();
            int T = in.nextInt();
            int P = in.nextInt();
            int Q = in.nextInt();

            if (N == 0 && M == 0 && T == 0 && P == 0 && Q == 0) return;

            int[][] sky = new int[N][M];

            for (int i = 0; i < N; i++) {
                String row = in.next();
                for (int j = 0; j < M; j++) {
                    sky[i][j] = row.charAt(j) == '*' ? 1 : 0;
                }
            }

            int[][] pattern = new int[P][Q];
            HashSet<Long> patternHashSet = new HashSet<Long>(T);

            for (int i = 0; i < T; i++) {
                for (int j = 0; j < P; j++) {
                    String row = in.next();
                    for (int k = 0; k < Q; k++) {
                        pattern[j][k] = row.charAt(k) == '*' ? 1 : 0;
                    }
                }
                patternHashSet.add(patternHash(pattern));
            }

            if (P > N || Q > M) {
                System.out.println(0);
                continue;
            }

            long[][]skyhash = skyHash(sky, P, Q);
            
            for (int i = 0; i < skyhash.length; i++) {
                for (int j = 0; j < skyhash[0].length; j++) {
                    long hash = skyhash[i][j];
                    if (patternHashSet.contains(hash)) {
                        patternHashSet.remove(hash);
                    }
                }
            }

            System.out.printf("Case %d: %d\n", num++, T - patternHashSet.size());
        }
    }
}
