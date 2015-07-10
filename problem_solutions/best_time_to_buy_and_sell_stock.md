# Best Time to Buy and Sell Stock.

This is a series of questions. It has four stages:

1. Can buy and sell unlimited times
2. Can buy and sell at most once
3. Can buy and sell at most twice
4. Can buy and sell at most $$k$$ times

The first stage is very simple, we just add all positive growth between any two adjoined stock prices.
From the second stage things becomes a little interesting.

If we can only buy and sell once, Let's assume we will sell at time $$i$$, when should we buy in?
Obviously, we should buy in that the lowest price of this stock from time point $$0$$ to $$i - 1$$.
If we can not find a lower price than the price at $$i$$, we'd rather not sell at $$i$$.
Thus, to solve this question, we just keep a variable record minimum price before 
and find the maximum revenue over each selling time point $$i$$ and that is the answer.

```java
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        
        int min = prices[0];
        int max = 0;
        
        for (int p : prices) {
            max = Math.max(max, p - min);
            min = Math.min(min, p);
        }
        
        return max;
    }
}
```

If we can buy twice, a direct think is to divide the time sequence of stock prices into two part.
We perform algorigthm for buying once respectively on left and right. If we do this by brute force
method, we will get an $$O(N^2)$$ time cost which is not acceptable. To improve, at first we can think of
a reverse buying once algorithm. Let's say we are buying the stock at $$i$$, when should we sell the stock?
Of course the best time to sell is the highest stock price after $$i$$.
If we perform forward algorithm of once algorithm and preserve the maximum revenue if we buy and sell once
before $$i$$, and then perform a backward algorithm to get maximum revenue to buy and sell once after $$i$$,
then record the maximum sum of two revenue, we will get the answer.

```java
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
}
```

Another solution to best time of buying and selling is applying a general dynamic programming
algorithm on $$k \ge 2$$.

Let's say we have a budget which is zero at first. The budget can be
overdraw so that we can get a negative budget. Every time we buy stock, we draw money from the
budget and put revenue to the budget after selling stock. So, if we'd like to sell for the last
time at time $$i$$, what would we like the budget now to be like? As we have already deduct
the amount of money from budget, so at this time the final revenue we be current budget plus
the final price directly. As current price at time $$i$$ is fixed, so the higher current budget
is, the higher the final revenue is. So we have to know the highest budget remains after buying
for the last time.

To get the highest budget after the last buying, We consider the budget another step backward.
I'd we get the highest budget after last buying if we get the highest budget after the one before
last selling. Push this backward repeatedly until we meet the first buying and selling, at each $$i$$,
we can get the answer for maximum for a sub array of prices between $$0$$ and $$i$$.

Note that if buy and sell time $$k$$ is so large that it exceeds the number of prices, the problem
falls back to stage one thus we will have a simple solution.

Below are solution for a general $$k$$ buying and selling problem:

!CODEFILE "../leetcode/BestTimeToBuyAndSellStockIV.java"

