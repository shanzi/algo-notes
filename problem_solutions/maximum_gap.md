# Maximum Gap

Make use of the similar idea of bucket sorting, we at first divide the whole number range into several
buckets and put each elements to its corresponding bucket. After that, we calculate maximum gap between
adjoined buckets to find maximum gap. We let buckets has a size same as the minimum possible value of maximum gap
so that we can just ignore gap between items in a same bucket. 

```java
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        
        int minval = Integer.MAX_VALUE;
        int maxval = Integer.MIN_VALUE;
        
        for (int n : nums) {
            minval = Math.min(minval, n);
            maxval = Math.max(maxval, n);
        }
        
        int minmaxgap = (maxval - minval) / (nums.length - 1);
        if ((maxval - minval) % (nums.length - 1) > 0) minmaxgap++;
        minmaxgap = Math.max(minmaxgap, 1);
        int bucketCount = (maxval - minval) / minmaxgap + 1;
        
        int[][]dp = new int[bucketCount][3];
        
        for (int n : nums) {
            int idx = (n - minval) / minmaxgap;
            if (dp[idx][0] == 0) {
                dp[idx][1] = n;
                dp[idx][2] = n;
            } else {
                dp[idx][1] = Math.min(dp[idx][1], n);
                dp[idx][2] = Math.max(dp[idx][2], n);
            }
            dp[idx][0]++;
        }
        
        int maxgap = 0;
        
        for (int i = 0; i < dp.length;) {
            if (dp[i][0] == 0) continue;
            int j = i + 1;
            while (j < dp.length && dp[j][0] == 0) j++;
            if (j < dp.length) {
                maxgap = Math.max(maxgap, dp[j][1] - dp[i][2]);
            }
            i = j;
        }
        
        return maxgap;
    }
}
```
