public class Solution {
    public int maxProfit(int[] prices) {
    	if (prices == null || prices.length == 0) {
    		return 0;
    	}
    	int maxP = 0;
    	int profit = 0;
    	int base = prices[0];
    	for (int i = 1; i < prices.length; i++) {
    		profit = prices[i] - base;
    		if (profit > maxP) {
    			maxP = profit;
    		}
    		if (prices[i] < base) {
    			base = prices[i];
    		}
    	}
    	return maxP;
    }
}
