/*
 * Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

Solution: crazy backtracking to outperforme bottom-up DP. There ought to be a top-down DP method if we can distinguish between true, false and unknown states.
 * */
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || s.length() == 0) {
            return isFullStar(p);
        }
        if (p == null || p.length() == 0) {
            return false;
        }
        
        int m = s.length(), n = p.length();
        int sBackup = -1, pBackup = -1;
        int i = 0, j = 0;
        while (i < m) {
        	if (j < n && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
        		i++;
        		j++;
        		// Do NOT use i==m to return value here. Allow i to increase if pBackup is a good point to stop j.
        	} else if (j < n && p.charAt(j) == '*') {
        		// Jump over all the consequtive '*'
        		while (j < n && p.charAt(j) == '*') {
        			j++;
        		}
        		if (j == n) return true;
        		// We can safely back up here, because the last else branch moving forward on s, but keep p on the last known '*' plus 1 until we are ready to moving both pointers forward or reach the end.
        		sBackup = i;
        		pBackup = j;
        	} else {
        		if (sBackup < 0) return false;
        		// The following part is backtracking on s, while keeping p on the last known '*' plus 1
        		sBackup++;
        		i = sBackup;
        		j = pBackup;
        	}        	
        }
        
        while (j < n && p.charAt(j) == '*') {
        	j++;
        }
        
        return i == m && j == n;        
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

/*
top-down DP, still time out on the extremely long inputs, which the greedy + backtrack solution
can stop very quickly
*/
public class Solution {
	private Boolean[][] mem;
    public boolean isMatch(String s, String p) {
        if (s == null || s.length() == 0) {
            return isFullStar(p);
        }
        if (p == null || p.length() == 0) {
            return false;
        }
        
        int m = s.length(), n = p.length();
        mem = new Boolean[m+1][n+1];
        for (int i = 0; i <= m; i++) {
        	for (int j = 0; j <= n; j++) {
        		mem[i][j] = null;
        	}
        }
        
        mem[0][0] = true;
        for (int i = 1; i <= n; i++) {
            mem[0][i] = mem[0][i-1] && p.charAt(i-1) == '*';
        }
        for (int i = 1; i <= m; i++) {
            mem[i][0] = false;
        }
        
        return getMatch(s, p, m, n);
    }
    
    private Boolean getMatch(String s, String p, int i, int j) {
    	if (mem[i][j] != null) {
    		return mem[i][j];
    	}
    	
    	char currentP = p.charAt(j-1);
        switch (currentP) {
        case '*':
            mem[i][j] = getMatch(s, p, i-1, j-1) || getMatch(s, p, i-1, j) || getMatch(s, p, i, j-1);
            break;
        case '?':
            mem[i][j] = getMatch(s, p, i-1, j-1);
            break;
        default:
            mem[i][j] = s.charAt(i-1) == currentP && getMatch(s, p, i-1, j-1); 
            break;
        }
        return mem[i][j];
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
