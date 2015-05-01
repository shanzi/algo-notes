import java.util.Arrays;

public class EllysPairs {
    public int getDifference(int[] knowledge) {
        if (knowledge.length == 0) return 0;
        Arrays.sort(knowledge);
        int min = Integer.MAX_VALUE;
        int max = 0;
        int n = knowledge.length;
        for (int i = 0; i < n / 2; i++) {
            int sum = knowledge[i] + knowledge[n - i - 1];
            if (sum > max) max = sum;
            if (sum < min) min = sum;
        }
        return max - min;
    }
}
