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
