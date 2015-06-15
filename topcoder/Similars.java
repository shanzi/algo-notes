public class Similars {
    public int maxsim(int L, int R) {

        int[] flags = new int[1<<10];

        for (int i = L; i <= R; i++) {
            int mask = 0;
            int n = i;
            while (n > 0) {
                mask |= (1 << (n % 10));
                n /= 10;
            }
            flags[mask]++;
        }

        int max = 0;
        for (int i = 0; i < flags.length; i++) {
            if (flags[i] > 1) max = Math.max(max, Integer.bitCount(i));
        }

        for (int i = 0; i < flags.length; i++) {
            if (flags[i] == 1) {
                for (int j = i + 1; j < flags.length; j++) {
                    if (flags[j] > 0) max = Math.max(max, Integer.bitCount(i & j));
                }
            }
        }

        return max;
    }
}
