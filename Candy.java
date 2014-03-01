/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Difficulty: Hard

Solution: Copied from http://www.cnblogs.com/TenosDoIt/p/3389479.html
I just don't see any online solution clearly explain how this is tiebreaked. They seem all give the answer
of 4 instead of 5 when the input is [1,2,2]. 4 might be the right answer if the requirement is to allow 
one child to get fewer than a neighboring child when they have the same ratings.

*/
public class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        
        int n = ratings.length;
        int[] count = new int[n];
        Arrays.fill(count, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                count[i] = count[i-1] + 1;
            }
        }
        
        for (int i = n-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1] && count[i] <= count[i+1]) {
                count[i] = count[i+1] + 1;
            }
        }
        
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += count[i];
        }
        
        return total;
    }
}
