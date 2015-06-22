import java.util.*;

public class SpaceWarDiv1 {
    private void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private void swap(long[] array, int i, int j) {
        long t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private void sort(int[] enemyStrength, long[] enemyCount) {
        for (int i = 0; i < enemyStrength.length; i++) {
            for (int j = i + 1; j < enemyStrength.length; j++) {
                if (enemyStrength[i] < enemyStrength[j]) {
                    swap(enemyStrength, i, j);
                    swap(enemyCount, i, j);
                }
            }
        }
    }
    public long minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength, long[] enemyCount) {
        Arrays.sort(magicalGirlStrength);
        sort(enemyStrength, enemyCount);

        if (magicalGirlStrength[magicalGirlStrength.length - 1] < enemyStrength[0]) return -1;

        long sum = 0;

        for (long count : enemyCount) {
            sum += count;
        }

        long[] f = new long[magicalGirlStrength.length];
        while (sum > 0) {
            int nleft = magicalGirlStrength.length;
            long average = sum / nleft;
            if (sum % nleft > 0) average++;
            for (int i = magicalGirlStrength.length - 1; i >= 0; i--) {
                int girlStrength = magicalGirlStrength[i];
                long av = average;

                for (int j = 0; j < enemyStrength.length; j++) {
                    if (girlStrength >= enemyStrength[j]) {
                        long cost = Math.min(enemyCount[j], av);
                        f[i] += cost;
                        enemyCount[j] -= cost;
                        sum -= cost;
                        av -= cost;
                    }
                }
            }
        }
        long res = 0;

        for (long v: f) {
            res = Math.max(res, v);
        }
        return res;
    }
}

