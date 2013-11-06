// Cleaner version making use of long type
public class Solution {
	public int sqrt(int x) {
		long min = 0;
		long max = x / 2 + 1;
	    while (min <= max)
	    {
	        long mid = (min + max) / 2;
	        long sq = mid * mid;
	        if (sq == x) return (int) mid;
	        else if (sq < x) min = mid + 1;
	        else max = mid - 1;
	    }
	    return (int) max;
	}
	}


// Ugly code
public class Solution {
    // NOTE: this has to use 2^31 instead of 2^32
	private static int UPPER = 46340; // sqrt(2^31)
    public int sqrt(int x) {
    	if (x == 0 || x == 1) {
    		return x;
    	} else if (x < 0) {
    		return -1;
    	}
        int min = 1;
        int max = Math.min(x,UPPER);
        int result = min;
        while (min <= max) {
        	if (max * max <= x) {
        		result = max;
        		break;
        	}
        	if (min * min > x) {
        		result = min - 1;
        		break;
        	}
        	int mid = min + (max - min)/2;
        	int square = mid * mid;
        	if (square == x) {
        		result = mid;
        		break;
        	} else if (square > x) {
        		max = mid - 1;
        	} else {
        		min = mid + 1;
        	}
        }
        return result;
    }
}
