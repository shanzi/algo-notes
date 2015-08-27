import java.io.*;
import java.util.*;

public class Stripies {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        PriorityQueue<Double> pq = new PriorityQueue<Double>(N, new Comparator<Double>() {
            public int compare(Double a, Double b) {
                if (a < b) return 1;
                else if (a > b) return -1;
                else return 0;
            }
        });
        for (int i = 0; i < N; i++) {
            double s = in.nextDouble();
            pq.offer(s);
        }
        while (pq.size() > 1) {
            double a = pq.poll();
            double b = pq.poll();
            pq.offer(2 * Math.sqrt(a * b));
        }

        System.out.printf("%.3f\n", pq.poll());
    }
}
