import java.util.Arrays;

public class MysticAndCandiesEasy {
    public int minBoxes(int C, int X, int[]high) {
        Arrays.sort(high);
        int remain = C - X;
        int sum = 0;
        for (int i = 0; i < high.length; i++) {
			sum += high[i];
            if (sum > remain) {
                return high.length - i;
            }
            if (sum == remain) {
				return high.length - i - 1;
			}
        }
        return high.length;
    }
}
