/*Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

The following DP solution times out on the largest input

Copied from https://github.com/guolinaileen/abc/blob/master/Palindrome%20Partitioning%20II.java

Difficulty : Hard
Solution: the key idea is to use palin array to memorize and get O(1) for each new filling into palin
and cuts comparison
 * */
public class Solution {
    public int minCut(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
         int length = s.length();
         int []dp=new int[length+1];
         boolean [][]palin=new boolean[length][length];
         //dp[i] stores the minimum number of cut
         for(int i = 0; i <= length; i++)
                dp[i] = length-i;

      for(int i = length-1; i >= 0; i--)
      {
        for(int j = i; j < length; j++)
        {
          if(s.charAt(i) == s.charAt(j) && (j-i<2 || palin[i+1][j-1]))
          {
            palin[i][j] = true;
            dp[i] = Math.min(dp[i],dp[j+1]+1);
          }
        }
      }
      return dp[0]-1;
    }
} 
 
// TIMEOUT 
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    private int[][] table;
    HashMap<String, Integer> hash;
    public int minCut(String s) {
        int n = s.length();
        hash = new HashMap<String, Integer>();
        
        return getMin(s, 0, n);
    }
    
    // start is inclusive, and end is exclusive
    private int getMin(String s, int start, int end) {
    	String key = s.substring(start, end);
    	if (!hash.containsKey(key)) {
    		int min = Integer.MAX_VALUE; 
    		if (isPalindrome(s, start, end-1)) {
    			min = 0;
    		} else {
    			for (int i = start + 1; i < end; i++) {
                    int left = getMin(s, start, i);
                    int right = getMin(s, i, end);
                    int current = left + right + 1;
                    if (current < min) {
                        min = current;
                    }
                    if (min == 1) {
                        break;
                    }
                }                
    		}
    		hash.put(key, min);
    	}
    	return hash.get(key);
    }
    
    private boolean isPalindrome(String s, int i, int j)
    {
        while(i<j)
        {
            if(s.charAt(i)!=s.charAt(j)) return false; 
            i++; 
            j--; 
        }
        return true; 
    }
}
