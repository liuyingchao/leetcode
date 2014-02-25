/*
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Solution: grow from a char in the middle to both directions to test palindrome. 3 tricks: we need to consider the string of
both odd and even lengths; we don't have to start with substring of 1 or 2 all the time--instead we can start with the current
known max--this is key to avoid timeout for extremely long strings; this implies the search should start from the middle
instead of from one end--this way we can better utilize the known max.

TBD: refactor to simplify the duplicate loop bodies.
 * */
 public class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int max = 1;
        String candidate = s.substring(0, 1);        
        
        int mid = (n-1)/2;
        int start, end;
        for (int i = mid; i >= 0; i--) {
        	int step = 1;
        	// Palindrome of odd length
        	start = i-step;
        	end = i+step;
        	while (start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
        		int len = 1 + step * 2;
        		if (len > max) {
        			max = len;
        			candidate = s.substring(start, end + 1);
        		}
        		step++;
        		start = i - step;
        		end = i + step;        				
        	}

        	// Palindrome of even length
        	step = 0;
        	start = i;
        	end = i + 1;
        	while (start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
        		int len = (step+1) * 2;
        		if (len > max) {
        			max = len;
        			candidate = s.substring(start, end + 1);
        		}
        		step++;
        		start = i - step;
        		end = i + step + 1;
        	}
        }
        for (int i = mid+1; i < n; i++) {
        	int step = 1;
        	// Palindrome of odd length
        	start = i-step;
        	end = i+step;
        	while (start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
        		int len = 1 + step * 2;
        		if (len > max) {
        			max = len;
        			candidate = s.substring(start, end + 1);
        		}
        		step++;
        		start = i - step;
        		end = i + step;        				
        	}

        	// Palindrome of even length
        	step = 0;
        	start = i;
        	end = i + 1;
        	while (start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
        		int len = (step+1) * 2;
        		if (len > max) {
        			max = len;
        			candidate = s.substring(start, end + 1);
        		}
        		step++;
        		start = i - step;
        		end = i + step + 1;
        	}
        }

        return candidate;
    }    
}


// V1: ugly code to call the isPalindrome() method
public class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int max = 0;
        String candidate = "";
        
        int mid = n/2;
        for (int i = mid; i >= 0; i--) {
        	// Palindrome of odd length
        	int step = max/2;
        	while (isPalindrome(s, i - step, i + step)) {
        		int len = 1 + step * 2;
        		if (len > max) {
        			max = len;
        			candidate = s.substring(i - step, i + step + 1);
        		}
        		step++;
        	}

        	// Palindrome of even length
        	step = max/2;
        	while (isPalindrome(s, i - step, i + 1 + step)) {
        		int len = (step+1) * 2;
        		if (len > max) {
        			max = len;
        			candidate = s.substring(i - step, i + step + 2);
        		}
        		step++;
        	}
        }
        for (int i = mid+1; i < n; i++) {
        	// Palindrome of odd length
        	int step = max/2;
        	while (isPalindrome(s, i - step, i + step)) {
        		int len = 1 + step * 2;
        		if (len > max) {
        			max = len;
        			candidate = s.substring(i - step, i + step + 1);
        		}
        		step++;
        	}

        	// Palindrome of even length
        	step = max/2;
        	while (isPalindrome(s, i - step, i + 1 + step)) {
        		int len = (step+1) * 2;
        		if (len > max) {
        			max = len;
        			candidate = s.substring(i - step, i + step + 2);
        		}
        		step++;
        	}
        }

        return candidate;
    }
    
    private boolean isPalindrome(String s, int start, int end) {
    	if (start < 0 || end >= s.length()) {
    		return false;
    	}
    	while (start < end) {
    		if (s.charAt(start) != s.charAt(end)) {
    			return false;
    		}
    		start++;
    		end--;
    	}
    	return true;
    }
}
