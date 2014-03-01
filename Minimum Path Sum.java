/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Difficulty: Easy

Solution: Straightforward DP
*/
public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] sum = new int[m][n];
        sum[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            sum[i][0] = grid[i][0] + sum[i-1][0];
        }
        
        for (int i = 1; i < n; i++) {
            sum[0][i] = grid[0][i] + sum[0][i-1];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = grid[i][j] + Math.min(sum[i-1][j], sum[i][j-1]);
            }
        }
        
        return sum[m-1][n-1];
    }
}
