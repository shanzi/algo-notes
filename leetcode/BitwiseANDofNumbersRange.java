public class BitwiseANDofNumbersRange {
    public static int rangeBitwiseAnd(int m, int n) {
        int l = m & n;
        for (int i = 0; i < 32; i++) {
            if (((l >> i) & 1) > 0) {
                int r = n & (~(1 << i)) | ((1 << i) - 1);
                if (r > m) {
                    l &= r;
                }
            }
        }
        return l;
    }
}
