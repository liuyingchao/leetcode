public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
        	return 0;
        }
        int N = prices.length;
        int sum = 0;
        for (int i = 1; i < N; i++) {
        	// Any upward price move is a chance to get profit. If the price move upwards 2 days
        	// in a row(say day 1,2, and 3), we just look at it as if we buy and sell immediately on day 2.
        	// The profit is the same as if buying on day 1 and selling on day 3.
        	if (prices[i] > prices[i-1]) {
        		sum += (prices[i] - prices[i-1]);
        	}
        }
        
        return sum;
    }
}
