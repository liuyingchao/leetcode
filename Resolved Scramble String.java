/*
Copied from http://blog.sina.com.cn/s/blog_b9285de20101gy6n.html

有三种情况需要考虑：

1. 如果两个substring相等的话，则为true

2. 如果两个substring中间某一个点，左边的substrings为scramble string，
同时右边的substrings也为scramble string，则为true

3. 如果两个substring中间某一个点，s1左边的substring和s2右边的substring为scramble
string, 同时s1右边substring和s2左边的substring也为scramble
string，则为true

Another solution from:
http://www.cnblogs.com/TenosDoIt/p/3452004.html
The DP solution is essentially the same as the following code. The recursive solution is also listed.

Difficulty: Hard
Solution: 3D bottom up DP. I personally think it more clearly when considering the length as the most
important dimension. The first and second dimension means: for s1's substring starting at index i,
whether s2's substring starting at index j with length of len are scramble strings with each other.
As the result, we leave many array elements meaningless, such as the whole [n][n][0] and the 3D "corners"
where there is no enough character to scramble between 2 substrings, but those are just fine places
we need to avoid hitting into.
*/
public class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if ((s1 == null && s2 != null) ||
            (s1 != null && s2 == null) ||
            (s1.length() != s2.length())) return false;
        
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n+1];
        for (int len = 1; len <= n; len++) { // The outer loop is based on the length of substrings
            for (int i = 0; i <= n-len; i++) {
                for (int j = 0; j <= n-len; j++) {
                    if (s1.substring(i, i+len).equals(s2.substring(j, j+len))) {
                        dp[i][j][len] = true;
                    } else {
                        // Starting with 1 char from s1.charAt(i) as the separator of comparing these 2 scenarios
                        for (int offset = 1; offset < len; offset++) {
                            if ((dp[i][j][offset] && dp[i+offset][j+offset][len-offset]) ||
                            (dp[i][j+len-offset][offset] && dp[i+offset][j][len-offset])) {
                                dp[i][j][len] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        return dp[0][0][n];
    }
}

public class Solution {
    public boolean isScramble(String s1, String s2)
    {
        int n=s1.length();
        boolean[][][] dp=new boolean[n][n][n+1];
        
        for(int i=n-1; i>=0; i--)
            for(int j=n-1; j>=0; j--)
                for(int k=1; k<=n-Math.max(i,j);k++)    // k is the length of the substring we are looking at
                {
                    if(s1.substring(i,i+k).equals(s2.substring(j,j+k)))
                        dp[i][j][k]=true;
                    else
                    {
                        for(int l=1; l<k; l++)
                        {
                            if(dp[i][j][l] && dp[i+l][j+l][k-l] || dp[i][j+k-l][l] && dp[i+l][j][k-l])
                            {
                                dp[i][j][k]=true;
                                break;
                            }
                        }
                    }
                }
        
        return dp[0][0][n];
    }
}
