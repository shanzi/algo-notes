import java.util.*;
public class PairGameEasy {
    private static class Pair {
        int x;
        int y;
        Pair(int a, int b) {
            x = a;
            y = b;
        }
    }
    public String able(int a, int b, int c, int d) {
        LinkedList<Pair> queue = new LinkedList<Pair>();
        queue.addLast(new Pair(a, b));

        while (!queue.isEmpty()) {
            Pair p = queue.pollFirst();
            if (p.x == c && p.y == d) {
                return "Able to generate";
            }

            if (p.x + p.y <= c) {
                queue.addLast(new Pair(p.x + p.y, p.y));
            }

            if (p.x + p.y <= d) {
                queue.addLast(new Pair(p.x, p.x + p.y));
            }
        }

        return "Not able to generate";
    }
}
