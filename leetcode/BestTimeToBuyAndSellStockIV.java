import java.util.*;

public class BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        if (k == 0) return 0;
        
        if (k >= prices.length / 2) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                res += Math.max(0, prices[i] - prices[i - 1]);
            }
            return res;
        }

        int[] sell = new int[k];
        int[] buy = new int[k];
        Arrays.fill(buy, Integer.MIN_VALUE);
        
        for (int v : prices) {
            for (int i = k - 1; i > 0; i--) {
                sell[i] = Math.max(sell[i], buy[i] + v);
                buy[i] = Math.max(buy[i], sell[i - 1] - v);
            }
            sell[0] = Math.max(sell[0], buy[0] + v);
            buy[0] = Math.max(buy[0], -v);
        }
        
        return sell[k - 1];
    }
}
