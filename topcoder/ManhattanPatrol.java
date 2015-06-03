import java.util.*;

public class ManhattanPatrol {
    public long countIntersections(int N,
                                   int AX,
                                   int BX,
                                   int MX,
                                   int AY,
                                   int BY,
                                   int MY) {
        int[] X = new int[N];
        HashMap<Integer, Integer> xyMap = new HashMap<Integer, Integer>();

        X[0] = BX;
        int Y = BY;
        xyMap.put(X[0], Y);

        for (int i = 1; i < N; i++) {
            X[i] = (X[i - 1] * AX + BX) % MX;
            Y = (Y * AY + BY) % MY;
            xyMap.put(X[i], Y);
        }

        Arrays.sort(X);

        for (int i = 0; i < N; i++) {
            X[i] = xyMap.get(X[i]);
        }

        long count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 3; j < N; j++) {
                int upper = Math.max(X[i], X[j]);
                int lower = Math.min(X[i], X[j]);
                long upperCount = 0;
                long lowerCount = 0;

                for (int k = i + 1; k < j; k++) {
                    if (X[k] > upper) upperCount++;
                    else if (X[k] < lower) lowerCount++;
                }

                count += upperCount * lowerCount;
            }
        }

        return count;
    }
}
