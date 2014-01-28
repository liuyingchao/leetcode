/* DP
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 * */
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.charAt(0) == '0') return 0;
        
        int n = s.length();
        int[] f = new int[n+1];
        f[0] = f[1] = 1;
        
        for (int i = 1; i < n; i++) {
        	char prev = s.charAt(i-1);
        	switch (s.charAt(i)) {
        	case '0':
    			if (prev == '0' || prev >= '3') {
    				return 0; // invalid input
    			} else {
    				f[i+1] = f[i-1];
    			}
    			break;
    		case '7':
    		case '8':
    		case '9':
    			if (prev == '1') {
    				f[i+1] = f[i] + f[i-1];
    			} else {
    				f[i+1] = f[i];
    			}
    			break;
			default: // '1'--'6'
				if (prev == '1' || prev == '2') {
					f[i+1] = f[i] + f[i-1];
				} else {
					f[i+1] = f[i];
				}
				break;
        	}        		
        }
        
        return f[n];
    }
}
