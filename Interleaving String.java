/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

Difficulty : Medium -- for some reason, I got stuck at constructing the induction formula

Solution: DP. What blocked seemed to be non-clear about the base case of one string being empty

*/

public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();    
        int n = s2.length();
        if (s3.length() != m + n) {
            return false;
        }
        
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            if (s1.charAt(i-1) == s3.charAt(i-1)) {
                dp[i][0] = true;
            } else 
                break;  // No need to keep checking the remaining chars against s1 and the empty s2
        }
        
        for (int i = 1; i <= n; i++) {
            if (s2.charAt(i-1) == s3.charAt(i-1)) {
                dp[0][i] = true;
            } else 
                break;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int s3Index = i + j - 1;
                dp[i][j] = (s3.charAt(s3Index) == s2.charAt(j-1) && dp[i][j-1]) ||
                            (s3.charAt(s3Index) == s1.charAt(i-1) && dp[i-1][j]);
            }
        }
        
        return dp[m][n];
    }
}
