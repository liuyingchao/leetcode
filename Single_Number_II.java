/*Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * */

public class Solution {
    public static int singleNumber(int[] A) {  
        int[] countsPerBit = new int[32];  
        int result = 0;  
        for(int i=0; i<32; i++){  
            for(int j=0; j<A.length; j++){  
                if(((A[j] >> i) & 1) == 1){  
                    countsPerBit[i] = (countsPerBit[i] + 1) % 3;  
                }  
            }  
            result |= (countsPerBit[i] << i);  
        }  
        return result;  
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
