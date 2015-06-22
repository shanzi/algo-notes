import java.util.*;

public class GreaterGame {
    public double calc(int[] hand, int[] sothe) {
        boolean[] numbers = new boolean[hand.length * 2];
        for (int n : hand) {
            numbers[n - 1] = true;
        }
        int sotheCount = 0;
        for (int n : sothe) {
            if (n > 0) {
                sotheCount++;
                numbers[n - 1] = true;
            }
        }

        int[] nhand = new int[hand.length - sotheCount];
        int[] nsothe = new int[hand.length - sotheCount];
        int l = -1;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == false) {
                nsothe[++l] = i + 1;
            }
        }

        Arrays.sort(hand);

        double res = 0;

        for (int i = 0; i < sothe.length; i++) {
            if (sothe[i] < 0) continue;

            l = 0;
            while (l < hand.length && hand[l] < sothe[i]) l++;

            if (l < hand.length) {
                res++;
            } else {
                l = 0;
                while (l < hand.length && hand[l] < 0) l++;
            }

            hand[l] = -1;
        }

        l = -1;
        for (int n : hand) {
            if (n < 0) continue;
            nhand[++l] = n;
        }

        /* 
         * for each our card, the expectation to win a score equals
         * the probability that it is matched with a card with lower number multipy 1.0
         * The expectation of total score equals to sum of all these expectations.
         */

        for (int n : nhand) {
            int count = 0;
            for (int m : nsothe) {
                if (n > m) count++;
            }
            res += count / (double)nhand.length;
        }

        return res;
    }
}
