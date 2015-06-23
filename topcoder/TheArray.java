public class TheArray {
    public static int find(int n, int d, int first, int last) {
        int max = first;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, (int)Math.min(first + d * (long)(i - 1), last + d * (long)(n - i)));
        }
        return max;
    }
}
