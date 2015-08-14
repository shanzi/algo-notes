import java.io.*;
import java.util.*;

public class FenceRepair {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            PriorityQueue<Long> pq = new PriorityQueue<Long>(N);

            for (int i = 0; i < N; i++) {
                pq.offer(in.nextLong());
            }

            long cost = 0;

            while (pq.size() > 1) {
                long a = pq.poll(), b = pq.poll();
                cost += a + b;
                pq.offer(a + b);
            }

            System.out.println(cost);
        }
    }
}
