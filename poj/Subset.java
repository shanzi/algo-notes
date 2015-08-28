import java.io.*;
import java.util.*;

public class Subset {
    private static long nearest(long[] array, long value) {
        int l = 0, r = array.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] >= value) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (r < 0) r = 0;
        if (l >= array.length) l = array.length - 1;
        if (Math.abs(array[l] - value) > Math.abs(array[r] - value)) return array[r];
        else return array[l];
    }
    private static void solve(long[] array) {
        int N = array.length;
        int firstHalf = N / 2 + (N & 1);
        int lastHalf = N / 2; 
        HashMap<Long, Integer> map = new HashMap<Long, Integer>(1 << firstHalf);

        long min = Long.MAX_VALUE;
        int minCount = Integer.MAX_VALUE;
        for (int i = 1; i < (1 << firstHalf); i++) {
            long sum = 0;
            int count = 0;
            for (int j = 0; j < firstHalf; j++) {
                if (((i >> j) & 1) == 1) {
                    sum += array[j];
                    count++;
                }
            }
            if (map.containsKey(sum)) {
                map.put(sum, Math.min(count, map.get(sum)));
            } else {
                map.put(sum, count);
            }
            sum = Math.abs(sum);
            if (min > sum) {
                min = sum;
                minCount = count;
            } else if (min == sum) {
                minCount = Math.min(minCount, count);
            }
        }

        long[] firstArray = new long[map.keySet().size()];
        int f = 0;
        for (long k : map.keySet()) firstArray[f++] = k;

        Arrays.sort(firstArray);

        for (int i = 1; i < (1 << lastHalf); i++) {
            long sum = 0;
            int count = 0;
            for (int j = 0; j < lastHalf; j++) {
                if (((i >> j) & 1) == 1) {
                    sum += array[firstHalf + j];
                    count++;
                }
            }

            long near = nearest(firstArray, -sum);
            if (Math.abs(sum + near) < Math.abs(sum)) {
                sum += near;
                count += map.get(near);
            }
            sum = Math.abs(sum);
            if (min > sum) {
                min = sum;
                minCount = count;
            } else if (min == sum) {
                minCount = Math.min(minCount, count);
            }
        }

        System.out.printf("%d %d\n", min, minCount);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int N = in.nextInt();
            if (N == 0) return;
            long[] array = new long[N];
            for (int i = 0; i < N; i++) array[i] = in.nextLong();
            solve(array);
        }
    }
}
