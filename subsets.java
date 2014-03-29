/*
 * Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 Solution: adding each new int element into the existing list. Be careful with how to write the code cleanly

*/
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
            int size = ret.size();
        	for (int j = 0; j < size; j++) {
        		ArrayList<Integer> sublist = new ArrayList<Integer>(ret.get(j));
        		sublist.add(s[i]);
        		ret.add(sublist);
        	}        	
        }
        return ret;
    }
}
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
        	ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
        	for (ArrayList<Integer> val : ret) {
        		ArrayList<Integer> current = new ArrayList<Integer>(val);
        		current.add(s[i]);
        		newList.add(current);
        	}        	
        	ret.addAll(newList);
        }
        return ret;
    }
}


// Refactor to get rid of unnecessary helper function
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


// Poorly written version
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
