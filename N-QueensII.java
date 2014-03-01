/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

Difficulty: Medium

Solution: Backtracking. Notice the details of off by 1 and what condition for each check, plus how the recursion stops
*/
public class Solution {
    int sum;
    public int totalNQueens(int n) {
        sum = 0;
        int[] rows = new int[n];
        process(rows, 0);
        return sum;
    }
    
    private void process(int[] rows, int line) {
        if (line == rows.length) {  // Use the line after the last line as the stopping point for recursion
            sum++;
            return;
        }
        
        for (int col = 0; col < rows.length; col++) {
            if (isValid(rows, line, col)) {
                rows[line] = col;
                process(rows, line + 1);    // Do not use "line++" here, because we need to reuse the same value of line on different columns within this level of recursion
            }
        }
    }
    
    private boolean isValid(int[] rows, int line, int col) {
        for (int i = 0; i < line; i++) {    // Notice "<", because we need to check against lines before this line
            if (rows[i] == col) return false;
            if (Math.abs(i-line) == Math.abs(rows[i] - col)) return false;
        }
        
        return true;
    }
}
