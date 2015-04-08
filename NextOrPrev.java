import java.util.Arrays;

public class NextOrPrev {
    public int getMinimum(int nextCost, int prevCost, String start, String goal) {
        int[] deltas = new int[26];
        boolean[] marks = new boolean[26];

        Arrays.fill(deltas, 0);
        Arrays.fill(marks, false);

        for (int i = 0; i < start.length(); i++) {
            char s = start.charAt(i);
            char g = goal.charAt(i);
            deltas[(int)(s - 'a')] = (int)(g - s);
            marks[(int)(s - 'a')] = true;
        }

        int top = -1;
        int cost = 0;

        for (int i = 0; i < 26; i++) {
            if (!marks[i]) continue;
            if (i + deltas[i] <= top) return -1;

            top = i + deltas[i];
            if (deltas[i] > 0) cost += nextCost * deltas[i];
            else cost += -prevCost * deltas[i];
        }

        return cost;
    }
}
