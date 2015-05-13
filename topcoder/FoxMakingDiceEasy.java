public class FoxMakingDiceEasy {
    public int theCount(int N, int K) {
        if (N < 6 || K > 2 * N - 5) {
            return 0;
        }

        int count = 0;
        for (int S = K; S <= 2 * N - 5; S++) {
            int n = Math.min(S - 1, N);
            int m = (2 * n - S + 1) / 2;
            count += m * (m - 1) * (m - 2) / 6;
        }

        return count * 2;
    }
}
