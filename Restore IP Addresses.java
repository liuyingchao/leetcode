/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/
public class Solution {

	private ArrayList<String> values; 
    public ArrayList<String> restoreIpAddresses(String s) {
    	values = new ArrayList<String>();
    	// We can tweak if condition to cut down to 1 return, but I really don't like the
    	// readability of (!(||...))
    	if (s == null || s.length() < 4 || s.length() > 16) {
    		return values;
    	}
    	
    	String[] candidate = new String[4];
        findPartial(s, 0, candidate);
        return values;
    }
    
    private void findPartial(String s, int level, String[] candidate) {
    	// Avoid overflow, and we need to probe at most 3 digits
    	int maxFieldSize = Math.min(3, s.length());
    	for (int i = 1; i <= maxFieldSize; i++) {
    		candidate[level] = s.substring(0, i);
    		if (isValidField(candidate[level])) {
    			if (level == 3) {
    				if (i == s.length()) {
        				// Build result
        				buildResult(candidate);    					
    				} // else s has unused digits ==> not valid, just move on
    			} else if (level < 3) {
    				findPartial(s.substring(i), level+1, candidate);
    			}
    		}
    	}
    }
    
    private void buildResult(String[] candidate) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(candidate[0]).append(".").append(candidate[1]).append(".").append(candidate[2]).append(".").append(candidate[3]);
    	values.add(sb.toString());
    }
    
    // Numbers of 2 or 3 digits cannot start with 0
    private boolean isValidField(String s) {
    	int val;
    	switch (s.length()) {
    		case 1:
    			return true;
    		case 2:
    			val = Integer.parseInt(s);
    	    	return (val >= 10);
    		case 3:
    			val = Integer.parseInt(s);
    	    	return (val < 256 && val >= 100);
    		default:
    			return false;    		
    	}
    }
}
public class Solution {
	
	private ArrayList<String> values; 
    public ArrayList<String> restoreIpAddresses(String s) {
    	values = new ArrayList<String>();
    	// We can tweak if condition to cut down to 1 return, but I really don't like the
    	// readability of (!(||...))
    	if (s == null || s.length() < 4 || s.length() > 16) {
    		return values;
    	}
    	
    	String[] candidate = new String[4];
        findPartial(s, 0, candidate);
        return values;
    }
    
    private void findPartial(String s, int level, String[] candidate) {
    	ArrayList<String> result = new ArrayList<String>();
    	// Avoid overflow, and we need to probe at most 3 digits
    	int maxFieldSize = Math.min(3, s.length());
    	for (int i = 1; i <= maxFieldSize; i++) {
    		candidate[level] = s.substring(0, i);
    		if (isValidField(candidate[level])) {
    			if (level == 3) {
    				if (i == s.length()) {
        				// Build result
        				buildResult(candidate);    					
    				} // else s has unused digits ==> not valid, just move on
    			} else {
    				findPartial(s.substring(i), level+1, candidate);
    			}
    		}
    	}
    }
    
    private void buildResult(String[] candidate) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(candidate[0]).append(".").append(candidate[1]).append(".").append(candidate[2]).append(".").append(candidate[3]);
    	values.add(sb.toString());
    }
    
    // Numbers of 2 or 3 digits cannot start with 0
    private boolean isValidField(String s) {
    	int val;
    	switch (s.length()) {
    		case 1:
    			return true;
    		case 2:
    			val = Integer.parseInt(s);
    	    	return (val >= 10);
    		case 3:
    			val = Integer.parseInt(s);
    	    	return (val < 256 && val >= 100);
    		default:
    			return false;    		
    	}
    }
}
