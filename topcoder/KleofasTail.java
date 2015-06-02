public class KleofasTail {
    public long countGoodSequences(long K, long A, long B) {
        if (K == 0) return B - A + 1;

        long l = K, r = K, count = 0;
        if (l % 2 == 0) r++;
        while (true) {
            if (l > B) break;
            if (r >= A) {
                count += Math.min(r, B) - Math.max(l, A) + 1;
            }

            r <<= 1; l <<= 1;
            r++;
        }
        return count;
    }
}
