import java.util.*;

public class PiecewiseLinearFunction {
    public int maximumSolutions(int[] Y) {
        int[] sortedY = Arrays.copyOf(Y, Y.length);
        Arrays.sort(sortedY);
        ArrayList<Integer> distinctY = new ArrayList<Integer>(sortedY.length);
        for (int i : sortedY) {
            if (distinctY.size() > 0 && distinctY.get(distinctY.size() - 1) == i) continue;
            distinctY.add(i);
        }
        int[] distarray = new int[distinctY.size()];
        int[] count = new int[distinctY.size()];
        int[] countrange = new int[distinctY.size()];

        for (int i = 0; i < distarray.length; i++) {
            distarray[i] = distinctY.get(i);
        }

        for (int i = 0; i < Y.length - 1; i++) {
            int l = Y[i];
            int r = Y[i + 1];
            if (l == r) return -1;
            else {
                int ll = Arrays.binarySearch(distarray, l);
                int rr = Arrays.binarySearch(distarray, r);
                for (int j = ll; j < rr; j++) {
                    count[j]++;
                    countrange[j]++;
                }
                for (int j = rr + 1; j <= ll; j++) {
                    count[j]++;
                }
                for (int j = rr; j < ll; j++) {
                    countrange[j]++;
                }
            }
        }
        int idx = Arrays.binarySearch(distarray, Y[Y.length - 1]);
        count[idx]++;
        int max = 0;
        for (int i : count) {
            if (i > max) max = i;
        }
        for (int i : countrange) {
            if (i > max) max = i;
        }
        return max;
    }
}
