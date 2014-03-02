/*
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

Solution: essentially the same as N-QueensII.java. The only difference is the output() method to build the array 
*/
public class Solution {
    private ArrayList<String[]> res;
    
    public ArrayList<String[]> solveNQueens(int n) {
        res = new ArrayList<String[]>();
        int[] rows = new int[n];
        process(rows, 0);
        return res;
    }
    
    private void process(int[] rows, int line) {
        if (line == rows.length) {  // Use the line after the last line as the stopping point for recursion
            res.add(output(rows));
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
    
    private String[] output(int[] rows) {
        String[] arr = new String[rows.length];
        for (int i = 0; i < rows.length; i++) {
            StringBuilder sb = new StringBuilder( count );
            for(int j = 0; j < rows.length; j++) {
                if (j == rows[i]) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                } 
            }
            arr[i] = sb.toString();
        }
        return arr;
    }
}
