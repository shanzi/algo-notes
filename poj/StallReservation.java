import java.io.*;
import java.util.*;

class Interval implements Comparable<Interval>{
    int x;
    int y;
    int id;
    public Interval(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public int compareTo(Interval that) {
        Interval a = this;
        Interval b = that;
        return ((a.x == b.x) ? b.y - a.y : a.x - b.x);
    }
}

public class StallReservation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Interval[] intervals = new Interval[N];
        for (int i = 0; i < N; i++) {
            intervals[i] = new Interval(in.nextInt(), in.nextInt(), i);
        }
        Arrays.sort(intervals);
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(N, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return (a.y == b.y) ? a.x - b.x : a.y - b.y;
            }
        });
        int count = 0;
        int[] arrange = new int[N];
        for (Interval i : intervals) {
            if ((!pq.isEmpty()) && pq.peek().y < i.x) {
                arrange[i.id] = arrange[pq.poll().id];
            } else {
                arrange[i.id] = (++count);
            }
            pq.offer(i);
        }
        System.out.println(count);
        for (int i = 0; i < N; i++) {
            System.out.println(arrange[i]);
        }
    }
}
