import java.util.*;

public class BuildingHeightsEasy {
    public int minimum(int M, int[] heights) {
        Arrays.sort(heights);
        int sum = 0;

        for (int i = 0; i < M; i++) {
            sum += heights[i];
        }

        int min = heights[M - 1] * M - sum;

        for (int i = M; i < heights.length; i++) {
            sum += heights[i] - heights[i - M];

            min = Math.min(min, heights[i] * M - sum);
        }

        return min;
    }
}
