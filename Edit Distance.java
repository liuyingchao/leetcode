/*
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
 * */
public class Solution {
    public int minDistance(String word1, String word2) {
    	int m = word1.length(), n= word2.length();
    	int[][] table = new int[m+1][n+1];
    	table[0][0] = 0;
    	for (int i = 1; i <= m; i++) {
    		table[i][0] = i;
    	}
    	for (int i = 1; i <= n; i++) {
    		table[0][i] = i;
    	}
    	
    	for (int i = 1; i <= m; i++) {
    		for (int j = 1; j <= n; j++) {
    			if (word1.charAt(i-1) == word2.charAt(j-1)) {
    				table[i][j] = table[i-1][j-1];
    			} else {
    				// Those 3 min operators corresponds to replace the last char, add char to whatever above it and delete char to match whatever on its left
    				table[i][j] = Math.min(table[i-1][j-1], Math.min(table[i-1][j], table[i][j-1])) + 1;
    			}
    		}
    	}
    	
    	return table[m][n];
    }
}
