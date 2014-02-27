/*
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

Difficulty: Medium
Solution: O(1) solution to utilize the first row and the first column for storage. The root reason is: if we mark any element in those 2 collections to zero
during scanning, we know for sure that particular element needs to be set to 0 in the end. So "prematurely" setting it to zero doesn't lead to any error.
So the whole process is broken into the following 4 steps as commented.
 * */
public class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Initialize
        boolean rowOneZero = false , columnOneZero = false;
        for (int i = 0; i < m; i++) {
        	if (matrix[i][0] == 0) {
        		columnOneZero = true;
        		break;
        	}
        }
        
        for (int i = 0; i < n; i++) {
        	if (matrix[0][i] == 0) {
        		rowOneZero = true;
        		break;
        	}
        }
        
        // Scan
        for (int i = 1; i < m; i++) {
        	for (int j = 1; j < n; j++) {
        		if (matrix[i][j] == 0) {
        			matrix[i][0] = 0;
        			matrix[0][j] = 0;
        		}
        	}
        }
        
        // Set others
        for (int i = 1; i < m; i++) {
        	if (matrix[i][0] == 0) {
        		for (int j = 1; j < n; j++) {
        			matrix[i][j] = 0;
            	}	
        	}	
        }        
        for (int i = 1; i < n; i++) {
        	if (matrix[0][i] == 0) {
        		for (int j = 1; j < m; j++) {
        			matrix[j][i] = 0;
            	}	
        	}	
        }
        
        // Set row one and column one
        if (rowOneZero) {
        	for (int i = 0; i < n; i++) {
        		matrix[0][i] = 0;
        	}
        }        
        if (columnOneZero) {
        	for (int i = 0; i < m; i++) {
        		matrix[i][0] = 0;
        	}
        }
    }
}
