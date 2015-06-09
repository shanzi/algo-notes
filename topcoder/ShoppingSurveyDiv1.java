import java.util.*;

public class ShoppingSurveyDiv1 {
    public int minValue(int N, int K, int[] s) {
        int sum = 0;
        for (int i : s) {
            sum += i;
        }
        Arrays.sort(s);
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            int n = N - count;
            int left = sum - n * (K - 1);
            if (left <= 0) break;

            int w = s.length - i;
            int m = left / (w - K + 1);
            if (left % (w - K + 1) > 0) m++;
            m = Math.min(count + m, s[i]) - count;
            count += m;
            sum -= m * w;
        }
        return count;
    }
}
