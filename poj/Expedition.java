import java.io.*;
import java.util.*;

class FuelStop {
    int distance;
    int fuel;

    public FuelStop(int d, int f) {
        distance = d;
        fuel = f;
    }
}

public class Expedition {
    private static void solve(int P, FuelStop[] stops) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10000, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        int tank = P;
        int stopCount = 0;
        for (int i = 0; i < stops.length; i++) {
            tank -= stops[i].distance;

            while (tank < 0 && !pq.isEmpty()) {
                tank += pq.poll();
                stopCount++;
            }

            if (tank < 0) {
                System.out.println(-1);
                return;
            } else {
                pq.offer(stops[i].fuel);
            }
        }

        System.out.println(stopCount);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            FuelStop[] stops = new FuelStop[N + 1];

            for (int i = 0; i < N; i++) {
                stops[i] = new FuelStop(in.nextInt(), in.nextInt());
            }

            int L = in.nextInt(), P = in.nextInt();

            for (int i = 0; i < N; i++) {
                stops[i].distance = L - stops[i].distance;
            }

            stops[N] = new FuelStop(L, 0);

            Arrays.sort(stops, new Comparator<FuelStop>() {
                public int compare(FuelStop a, FuelStop b) {
                    return a.distance - b.distance;
                }
            });

            for (int i = N; i > 0; i--) {
                stops[i].distance -= stops[i - 1].distance;
            }

            solve(P, stops);
        }
    }
}
