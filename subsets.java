import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public ArrayList<ArrayList<Integer>> subsets(int[] s) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        ret.add(new ArrayList<Integer>());
        if (s == null || s.length == 0) {
        	return ret;
        }
        // Must sort first to automatically get each ArrayList in ascending order
        Arrays.sort(s);
        int N = s.length;
        for (int i = 0; i < N; i++) {
        	int newVal = s[i];
        	int length = ret.size();
        	for (int j = 0; j < length; j++) {
        		ArrayList<Integer> member = ret.get(j);
        		// Be careful to create a new clone instead of messing with the original member
        		ArrayList<Integer> clone = new ArrayList<Integer>();
        		for (Integer val : member) {
        			clone.add(val);
        		}
        		clone.add(newVal);
        		ret.add(clone);
        	}        	
        }
        return ret;
    }
}
