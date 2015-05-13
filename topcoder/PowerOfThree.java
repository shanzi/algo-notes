public class PowerOfThree {
    public String ableToGet(int x, int y) {
        int[] res = new int[30];
        getRes(res, x);
        getRes(res, y);

        int i;
        for (i = 0; i < res.length; i++) {
            if (res[i] > 1) return "Impossible";
            if (res[i] == 0) break;
        }

        for (; i < res.length; i++) {
            if (res[i] != 0) return "Impossible";
        }

        return "Possible";
    }

    void getRes(int[] res, int v) {
        for (int i = 0; i < res.length && v != 0; i++, v /= 3) {
            int r = v % 3;
            if (r != 0) {
                res[i]++;
            }
            if (r == 1 || r == -2) v--;
            else if (r == -1 || r == 2) v++;
        }
    }
}
