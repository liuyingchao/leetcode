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
        	helper(newVal, ret);        	
        }
        return ret;
    }
    
    public void helper(int newVal, ArrayList<ArrayList<Integer>> set) {
    	int length = set.size();
    	for (int i = 0; i < length; i++) {
    		ArrayList<Integer> member = set.get(i);
    		ArrayList<Integer> clone = new ArrayList<Integer>();
    		for (Integer val : member) {
    			clone.add(val);
    		}
    		clone.add(newVal);
    		set.add(clone);
    	}
    }
}
