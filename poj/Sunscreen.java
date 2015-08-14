import java.io.*;
import java.util.*;

class Cow implements Comparable<Cow> {

    int max;
    int min;

    public Cow(int min, int max) { 
        this.min = min;
        this.max = max;
    }

    public int compareTo(Cow b) {
        return this.min - b.min;
    }
}

class Lotion implements Comparable<Lotion> {

    int spf;
    int count;

    public Lotion(int spf, int count) { 
        this.spf = spf;
        this.count = count;
    }

    public int compareTo(Lotion b) {
        return this.spf - b.spf;
    }
}

public class Sunscreen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int C = in.nextInt(), L = in.nextInt();

            Cow[] cows = new Cow[C];
            Lotion[] lotions = new Lotion[L];

            for (int i = 0; i < C; i++) {
                cows[i] = new Cow(in.nextInt(), in.nextInt());
            }

            for (int i = 0; i < L; i++) {
                lotions[i] = new Lotion(in.nextInt(), in.nextInt());
            }

            Arrays.sort(cows);
            Arrays.sort(lotions);

            int c = 0;
            int l = 0;
            int count = 0;

            PriorityQueue<Cow> pq = new PriorityQueue<Cow>(C, new Comparator<Cow>() {
                public int compare(Cow a, Cow b) {
                    return a.max - b.max;
                }
            });

            while (l < L) {
                Lotion lo = lotions[l++];

                while (c < C && cows[c].min <= lo.spf) {
                    pq.offer(cows[c++]);
                }

                while (lo.count > 0 && !pq.isEmpty()) {
                    Cow cow = pq.poll();
                    if (cow.max >= lo.spf) {
                        count++;
                        lo.count--;
                    }
                }
            }

            System.out.println(count);
        }
    }
}
