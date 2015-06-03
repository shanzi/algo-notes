import java.util.*;

public class ColorTheCells {
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private int generatePermutation(int[] order, int[]dryingTime, int k) {
        if (k == order.length) {
            return calc(order, dryingTime);
        }

        int min = Integer.MAX_VALUE;
        for (int i = k; i < order.length; i++) {
            swap(order, k, i);
            min = Math.min(min, generatePermutation(order, dryingTime, k + 1));
            swap(order, k, i);
        }
        return min;
    }

    private int calc(int[] order, int[] dryingTime) {
        int n = order.length;
        int[] minTime = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < (1 << n) ; i++) {
            Arrays.fill(minTime, 0);
            min = Math.min(min, getTime(order, i, dryingTime, minTime));
        }
        return min;
    }

    private int getTime(int[]order, int direction, int[] dryingTime, int[] minTime) {
        int crt = 0;
        int time = 0;
        for (int cell : order) {
            int d = (direction >> cell & 1);
            int to = cell;
            if (d == 0) to--;
            else to++;
            if (to < 0 || to >= order.length) return Integer.MAX_VALUE;
            int bias = (to - crt) > 0 ? 1 : -1;

            while (crt != to) {
                int next = crt + bias;
                if (time < minTime[next]) time = minTime[next];
                crt = next;
                time++;
            }

            time++;
            minTime[cell] = time + dryingTime[cell];
        }
        return time;
    }

    public int minimalTime(int[] dryingTime) {
        int[] order = new int[dryingTime.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        return generatePermutation(order, dryingTime, 0);
    }
}
