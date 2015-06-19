/*
 * You can buy **at most** two times
 */

public class BestTimeToBuyAndSellStockIII {
    // Solution one, forward and backward. Cost time O(n) and space O(n)
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        
        int[] maxforward = new int[prices.length];
        int min = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            maxforward[i] = Math.max(maxforward[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        
        int max = 0;
        int maxback = 0;
        int maxtop = 0;
        
        for (int i = prices.length - 1; i > 0; i--) {
            maxback = Math.max(maxback, maxtop - prices[i]);
            maxtop = Math.max(maxtop, prices[i]);
            max = Math.max(max, maxforward[i - 1] + maxback);
        }
        
        return Math.max(max, maxforward[maxforward.length - 1]);
    }

    // DP solution, O(n) time and O(1) space
    
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;

        int sell1 = 0;
        int sell2 = 0;
        int buy1 = Integer.MIN_VALUE;
        int buy2 = Integer.MIN_VALUE;

        for (int v: prices) {
            sell2 = Math.max(sell2, buy2 + v);
            buy2 = Math.max(buy2, sell1 - v);
            sell1 = Math.max(sell1, buy1 + v);
            buy1 = Math.max(buy1, -v);
        }

        return sell2;
    }
}

