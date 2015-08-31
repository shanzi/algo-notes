import java.io.*;
import java.util.*;

public class BackwardDigitSums {
    private static boolean nextPermuation(int[] nums) {
        if (nums.length == 0) return false;
        int i = nums.length - 1;
        while (i > 0) {
            if (nums[i - 1] < nums[i]) break;
            i--;
        }
        if (i <= 0) return false;
        int j = nums.length - 1;
        while (nums[j] <= nums[i - 1] && j >= i) j--;
        int t = nums[j];
        nums[j] = nums[i - 1];
        nums[i - 1] = t;
        Arrays.sort(nums, i, nums.length);
        return true;
    }
    private static int nextSubset(int comb) {
        int x = comb & -comb, y = comb + x;
        return ((comb & ~y) / x >> 1) | y;
    }
    private static int sumNumbers(int[] numbers) {
        int N = numbers.length;
        int sum = 0;
        int t = 1;
        for (int i = 0; i < N; i++) {
            sum += t * numbers[i];
            t = (N - i - 1) * t / (i + 1);
        }
        return sum;
    }
    private static void solve(int N, int sum) {
        int comb = (1 << N) - 1;
        int[] numbers = new int[N];
        while (comb < (1 << 10)) {
            int l = 0;
            for (int i = 0; i < 10; i++) if (((comb >> i) & 1) == 1) numbers[l++] = i + 1;

            do {
                if (sum == sumNumbers(numbers)){
                    System.out.printf("%d", numbers[0]);
                    for (int i = 1; i < N; i++) {
                        System.out.printf(" %d", numbers[i]);
                    }
                    System.out.println();
                    return;
                }
            } while (nextPermuation(numbers));

            comb = nextSubset(comb);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), sum = in.nextInt();
        solve(N, sum);
    }
}
