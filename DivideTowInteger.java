/*
Divide Two Integers Total Accepted: 5444 Total Submissions: 35355 My Submissions
Divide two integers without using multiplication, division and mod operator.
*/
// A cleaner solution using long to save the hassel of dealing with overflow/underflow
public class Solution {
    public int divide(int dividend, int divisor) {
    	//boolean isNeg = dividend > 0 && divisor < 0 ||
    	//		dividend < 0 && divisor > 0;
    	// The following is a more elegant logic
    	boolean isNeg = (dividend < 0);
    	if (divisor < 0) isNeg = !isNeg;
    	long dividendL = Math.abs((long) dividend);
    	long divisorL = Math.abs((long) divisor);
    	
    	int res = 0;
        while (dividendL >= divisorL) {
        	long div = divisorL;
        	long current = 1;
        	while (dividendL >= div << 1) {
        		div <<= 1;
        		current <<= 1;
        	}
        	dividendL -= div;
        	res += current;
        }
        
        return isNeg ? -res : res;
    }
}

// My original code
public class Solution {
    public int divide(int dividend, int divisor) {
      // MY NOTE: revisit whether the following special cases can be refactored
        if (dividend == divisor) {
    		return 1;
    	} else if (dividend == 0) {
    		return 0;
    	} else if (dividend + divisor == 0) {
    		return -1;
    	} else if (divisor == Integer.MIN_VALUE) {
    		return 0;
    	} else if (divisor == 1) {
    	    // This branch has to be ahead of "isMin" checking. Otherwise, we'll get wrong result thanks to the ++ in that branch
    	    return dividend;
    	} else if (divisor == -1) {
    		return -dividend;
    	}
    	
    	boolean isMin = false;
    	if (dividend == Integer.MIN_VALUE) {
    		isMin = true;
    		dividend++;
    	}
    	
        boolean isNeg = (dividend < 0);
        if (divisor < 0) isNeg = !isNeg;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
      
        int res = 0;
        while (dividend >= divisor) {
      	    int current = 1;
      	    int mult = divisor;
      	    while (dividend >= mult << 1 && mult << 1 > 0) {
      		    mult = mult << 1;
      		    if (current == 0) {
      			    current = 1;
      		    }
      		current = current << 1;        		
      	}
      	res += current;
      	dividend -= mult;
      }
      
      // If we happen to do isMin and get the residual off by 1
      if (isMin && dividend == divisor - 1) { res++;}
      return isNeg ? -res : res;
    }
}
