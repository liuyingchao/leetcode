/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Difficulty: Hard

Solution: DP with 2 directions on a linear line. This is a very special DP problem where we need to scan backward to
utilize the DP result we have built in the first scan. It also uses "Divide and Conquer" by dividing the range into
2 parts considering we were asked about exactly 2 transactions. It feels natural to split that one.

*/
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        
        // Scan forward to get maxProfitByDay i array
        int lowest = prices[0];
        int maxP = 0;
        int[] maxPByDay = new int[n];
        for (int i = 0; i < n; i++) {
            int profit = prices[i] - lowest;
            if (profit > maxP) {
                maxP = profit;
            }
            // Set this array value regardless of whether we reach it exactly at Day i
            maxPByDay[i] = maxP;
            if (prices[i] < lowest) {
                lowest = prices[i];
            }
        }
        
        // Then scan backward to get the mex profit from day i to the last day
        // Combine as we scan
        int highest = prices[n-1];
        maxP = 0;
        int maxTotal = maxPByDay[n-1];
        for (int i = n - 1; i >= 0; i--) {
            int profit = highest - prices[i];
            if (profit > maxP) {
                maxP = profit;
            }
            int total = maxP + maxPByDay[i];
            if (total > maxTotal) {
                maxTotal = total;
            }
            if (prices[i] > highest) {
                highest = prices[i];
            }
        }
        
        return maxTotal;
    }
}
