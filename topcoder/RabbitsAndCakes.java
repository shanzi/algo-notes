public class RabbitsAndCakes {
    public long getNumber(int minR, int maxR, int minC, int maxC) {
        long count = 0;
        for (int i = 2; i <= maxR; i++) {
            for (int j = 1; j * i <= maxR; j++) {
                if (j * i < minR)
                    continue;
                int curC = (i - 1) * j;
                if (curC >= minC && curC <= maxC)
                    result++;
            }
        }
        for (int i = minR; i <= maxR; i++) {
            int to = maxC;
            int from = Math.max(i, minC);
            if (to >= from)
                result += to - from + 1;
        }
        return result;
    }
}

