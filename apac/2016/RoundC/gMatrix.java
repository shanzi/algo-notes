import java.io.*;
import java.util.*;

public class gMatrix {
    static private long[][] rowMax = new long[3020][3020];
    static private long[] A = new long[3020];
    static private long[] B = new long[3020];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), K = in.nextInt();
            long C = in.nextLong(), X = in.nextLong();
            for (int i = 1; i <= N; i++) A[i] = in.nextLong();
            for (int i = 1; i <= N; i++) B[i] = in.nextLong();

            for (int i = 1; i <= N; i++) {
                LinkedList<Long> increasingQueue = new LinkedList<Long>();
                for (int j = 1; j <= N; j++) {
                    long V = (A[i] * i % X + B[j] * j % X + C % X) % X;
                    while (!increasingQueue.isEmpty() && increasingQueue.getLast() < V) {
                        increasingQueue.removeLast();
                    }
                    increasingQueue.addLast(V);
                    if (j - K > 0) {
                        long VV = (A[i] * i % X + B[j - K] * (j - K) % X + C % X) % X;
                        if (increasingQueue.getFirst() == VV) increasingQueue.removeFirst();
                    }
                    rowMax[i][j] = increasingQueue.getFirst();
                }
            }

            long sum = 0;
            for (int j = 1; j <= N; j++) {
                LinkedList<Long> increasingQueue = new LinkedList<Long>();
                for (int i = 1; i <= N; i++) {
                    while (!increasingQueue.isEmpty() && increasingQueue.getLast() < rowMax[i][j])
                        increasingQueue.removeLast();
                    increasingQueue.addLast(rowMax[i][j]);
                    if (i - K > 0 && increasingQueue.getFirst() == rowMax[i - K][j])
                        increasingQueue.removeFirst();
                    if (i >= K && j >= K) sum += increasingQueue.getFirst(); 
                }
            }

            System.out.printf("Case #%d: %d\n", t + 1, sum);
        }
    }
}
