import java.util.*;
public class BoxesDiv2 {
    public int findSize(int[] candyCounts) {
        PriorityQueue<Integer> pqueue = new PriorityQueue<Integer>();
        for (int c : candyCounts) {
            pqueue.offer(getBox(c));
        }

        while (pqueue.size() > 1) {
            int a = pqueue.poll();
            int b = pqueue.poll();
            pqueue.offer(Math.max(a, b) * 2);
        }

        return pqueue.poll();
    }

    private int getBox(int c) {
        int box = 1;
        while(box < c) box <<= 1;
        return box;
    }
}
