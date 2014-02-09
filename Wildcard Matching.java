/* The following DP solution times out on extremely long inputs, as it builds a lot of redundant table cells
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || s.length() == 0) {
            return isFullStar(p);
        }
        if (p == null || p.length() == 0) {
            return false;
        }
        
        int m = s.length(), n = p.length();
        boolean[][] mem = new boolean[m+1][n+1];
        
        mem[0][0] = true;
        for (int i = 1; i <= n; i++) {
            mem[0][i] = mem[0][i-1] && p.charAt(i-1) == '*';
        }
        for (int i = 1; i <= m; i++) {
            mem[i][0] = false;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char currentP = p.charAt(j-1);
                switch (currentP) {
                case '*':
                    mem[i][j] = mem[i-1][j-1] || mem[i][j-1] || mem[i-1][j];
                    break;
                case '?':
                    mem[i][j] = mem[i-1][j-1];
                    break;
                default:
                    mem[i][j] = mem[i-1][j-1] && s.charAt(i-1) == currentP;
                    break;
                }
            }
        }
        
        return mem[m][n];
    }
    
    private boolean isFullStar(String s) {
        if (s == null || s.length() == 0) return true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}
