/*Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Solution: notice for the bit AND operator, we need a paranthethis. Otherwise, Java complains about the order
of operators
 * */

public class Solution {
    public int singleNumber(int[] A) {
    	int res = 0;
    	int n = A.length;
    	for (int i = 0; i < 32; i++) {
    		int count = 0;
    		int bit = 1 << i;
    		for (int j = 0; j < n; j++) {
    			if ((bit & A[j]) != 0) {
    				count++;
    			}
    		}
    		if (count % 3 != 0) {
    			res |= bit;
    		}
    	}
    	return res;
    }
}


import java.util.HashMap;

public class Solution {
    public int singleNumber(int[] A) {
        // MY NOTE: this method uses hash, while the question was asking about whether
        // we can solve the problem without using extra memory.
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int a : A) {
            if (hash.containsKey(a)) {
                int val = hash.get(a);
                hash.put(a, val+1);
            } else {
                hash.put(a, 1);
            }
        }
        
        int result = 0;
        for (int a : A) {
            if (hash.get(a) == 1) {
                result = a;
                break;
            }
        }
        return result;
    }
}
