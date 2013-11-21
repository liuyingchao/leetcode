import java.lang.Character;
public class Solution {
    // There is a Character.isAlphabetic() method. I haven't figured out how to use it.
    // Leetcode seems to focus on English and numbers only
    public boolean isPalindrome(String s) {
        if (s == null || s.trim().length() == 0) {
        	return true;
        }
        
        int N = s.length();
        int front = 0, back = N - 1;
        while (front <= back) {
        	while (front < back && !isValid(s.charAt(front))) {
        		front++;
        	}
        	
        	while (front < back && !isValid(s.charAt(back))) {
        		back--;
        	}
        	
        	if (front >= back) {
        		return true;
        	}
        	
        	if (Character.toUpperCase(s.charAt(front)) != Character.toUpperCase(s.charAt(back))) {
        		return false;
        	}
        	front++;
        	back--;
        }
        
        return true;
    }
    
    private boolean isValid(char c) {
    	return c >= 'a' && c <= 'z' ||
    			c >= 'A' && c <= 'Z' ||
    			c >= '0' && c <= '9';    			
    }
}
