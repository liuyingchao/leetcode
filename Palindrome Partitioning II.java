/*Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

The following DP solution times out on the largest input

 * */
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
