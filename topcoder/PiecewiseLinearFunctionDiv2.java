public class PiecewiseLinearFunctionDiv2 {
    private int count(int[] Y, int q) {
        int count = 0;
        for (int i = 0; i < Y.length - 1; i++) {
            if ((Y[i] <= q && Y[i + 1] > q) || (Y[i] >= q && Y[i + 1] < q)) {
                count++;
            } else if (Y[i] == Y[i + 1] && Y[i] == q) {
                return -1;
            }
        }

        if (q == Y[Y.length - 1]) count++;
        return count;
    }

    public int[] countSolutions(int[] Y, int[] query) {
        int[] res = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            res[i] = count(Y, query[i]);
        }
        return res;
    }
}
