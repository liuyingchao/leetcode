/* DP
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

Solution: memorize what we have known so far, so that we can reach "true" conclusion quickly for a valid spot.
In the worst case, this is just an O(n^2) solution without much performance saving
*/
import java.util.Set;

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return false;
        int N = s.length();
        boolean[] canBreak = new boolean[N+1];
        canBreak[0] = true;
        
        for (int i = 1; i <= N; i++) {
            // Here I rely on the default initialization of boolean[i] == false in Java.
            // Otherwise it should be explicitly set to false before running the inner loop
        	for (int j = i - 1; j >= 0; j--) {
        		if (canBreak[j] && dict.contains(s.substring(j, i))) {
        			canBreak[i] = true;
        			break;  // For inner loop. No need to keep looping all the way to j == 0
        		}
        	}
        }
        
        return canBreak[N];
    }
}
