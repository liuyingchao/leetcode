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

/* The following is copied C++ code
*/
class Solution {
public:
    bool isMatch(const char *s, const char *p) {
        const char *sBackup = NULL, *pBackup = NULL;
        while (*s != '\0') {
            if (*p == '?' || *s == *p) { // At this point we may have skipped consecutive '*'. The key is at this point, *s needs to match *p
                s++;
                p++;
            } else if (*p == '*') {
                while (*p == '*') p++;
                if (*p == '\0') return true;
                // Jump over the consecutive '*' in p so that we can match the next matching character in s with *p
                sBackup = s;
                pBackup = p;
            } else {
                if (!sBackup) return false;
                s = ++sBackup;
                p = pBackup;
            }
        }
        while (*p == '*') p++;
        return *s == '\0' && *p == '\0';
    }
};
