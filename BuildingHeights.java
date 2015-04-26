import java.util.Arrays;

public class BuildingHeights {
    private int minimum(int[] heights, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += heights[i];
        }
        sum = heights[n - 1] * n - sum;
        int min = sum;
        for (int i = n; i < heights.length; i++) {
            sum -= heights[i - 1] - heights[i - n];
            sum += (heights[i] - heights[i - 1]) * (n - 1);
            if (sum < min) {
                min = sum;
            }
        }
        return min;
    }

    public int minimum(int heights[]) {
        Arrays.sort(heights);
        int res = 0;
        for (int n = 2; n <= heights.length; n++) {
            res ^= minimum(heights, n);
        }
        return res;
    }
}
