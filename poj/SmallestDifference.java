import java.io.*;
import java.util.*;

public class SmallestDifference {
    private static boolean nextPermuation(int[] nums) {
        if (nums.length == 0) return false;
        int i = nums.length - 1;
        while (i > 0) {
            if (nums[i - 1] < nums[i]) break;
            i--;
        }
        if (i <= 0) {
            for (int j = 0; j < nums.length / 2; j++) {
                int t = nums[j];
                nums[j] = nums[nums.length - j - 1];
                nums[nums.length - j - 1] = t;
            }
            return false;
        }
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
    private static long getNumber(int[] nums) {
        long num = 0;
        for (int n : nums) {
            num = num * 10 + n;
        }
        return num;
    }
    private static void solve(int[] ints) {
        int N = ints.length;

        int k = N / 2;
        int comb = (1 << k) - 1;
        int[] a = new int[k];
        int[] b = new int[N - k];
        long min = Long.MAX_VALUE;
        while (comb < (1 << N)) {
            int l = 0, r = 0;
            for (int i = 0; i < N; i++) {
                if (((comb >> i) & 1) == 1) {
                    a[l++] = ints[i];
                } else {
                    b[r++] = ints[i];
                }
            }

            if (a[0] == 0 && a.length > 1) {
                comb = nextSubset(comb);
                continue;
            }

            do {
                long anum = getNumber(a);
                do {
                    if (b[0] == 0 && b.length > 1) continue;
                    long bnum = getNumber(b);
                    min = Math.min(min, Math.abs(anum - bnum));
                } while (nextPermuation(b));
            } while (nextPermuation(a));
            comb = nextSubset(comb);
        }
        System.out.println(min);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for (int t = 0; t < T; t++) {
            String s = in.readLine();
            char[] chars = s.replaceAll(" ", "").toCharArray();
            int[] ints = new int[chars.length];
            for (int i = 0; i < ints.length; i++) ints[i] = chars[i] - '0';
            solve(ints);
        }
    }
}
