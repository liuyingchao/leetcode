
/*
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

Difficulty: Hard

Solution: first DP to set up the trakcing information, where each valid track element is not null AND having a list of prev indices,
except for history.get(0). Then dfs like a postorder tree traversal to build the result set. Think about the end of the input string
like the root of a tree, and each node back to the string'a beginning like a tree node of the state tree.

 * */
public class Solution {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
    	ArrayList<String> res = new ArrayList<String>();
    	 if (s == null || s.length() == 0 || dict == null || dict.isEmpty()) {
         	return res;
         }
         int n = s.length();
         ArrayList<ArrayList<Integer>> history = new ArrayList<ArrayList<Integer>>();
         history.add(new ArrayList<Integer>()); 
         for (int i = 1; i <= n; i++) {
        	 ArrayList<Integer> current = null;
        	 for (int j = i - 1; j >= 0; j--) {
          		if (history.get(j) != null && dict.contains(s.substring(j, i))) {
          			if (current == null) {
          				current = new ArrayList<Integer>(); 
          			}
          			current.add(j);
          		}
          	 }
        	 history.add(current);
         }
         
         if (history.get(n) != null) {
        	 ArrayList<Integer> candidate = new ArrayList<Integer>();
        	 candidate.add(n);
        	 dfs(s, history, n, res, candidate);
         }
         return res;
    }
    
    private void dfs(String s, ArrayList<ArrayList<Integer>> history, int index, ArrayList<String> res, ArrayList<Integer> candidate) {
    	int len = candidate.size();
    	//int lastIndex = candidate.get(len-1);
    	if (index == 0) {	// We run into a valid result
    		int start = 0;
    		StringBuffer sb = new StringBuffer();
			// A valid result must have the invariant that: index == 0, and len >= 2
			// candidate.get(0) must have the value n as we set in the beginning of the recursion,
			// so that we know how to conclude the last substring into the current result string
			for (int i = len - 2; i >= 0; i--) {
				String tail = (i == 0) ? "" : " ";
    			sb.append(s.substring(start, candidate.get(i)) + tail);
    			start = candidate.get(i);
    		}
    		res.add(sb.toString());
    		return;
    	}
    	
    	ArrayList<Integer> currentList = history.get(index);
    	for (Integer i : currentList) {
    		candidate.add(i);
    		dfs(s, history, i, res, candidate);
    		candidate.remove(candidate.size() - 1);
    	}
    }    
}
