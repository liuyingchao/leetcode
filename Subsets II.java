/*
Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

Difficulty : Medium
Solution: The idea is based on the insertion order of subsets.java. The trick here is: after sorting,
we treat the adjacent duplicate elements as a group to handle. So we take the partial list we have so far,
for each member of the partial list, repeatedly adding the dup for once, twice, up until the "total" times.
This will give us an orderly non-repeated result.
*/
public class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] s) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        ret.add(new ArrayList<Integer>());
        if (s == null || s.length == 0) {
        	return ret;
        }
        // Must sort first to automatically get each ArrayList in ascending order
        Arrays.sort(s);
        int N = s.length;
        int slow = 0;
        int fast = 0;
        while (slow < N) {
            int current = s[slow];
            int total = 1;
            fast = slow + 1;
            while (fast < N && s[fast] == current) {
                total++;
                fast++;
            }
        
            int size = ret.size();
        	for (int j = 0; j < size; j++) {
        	    ArrayList<Integer> listMember = ret.get(j);
        	    // Insert the number of "count" current value into the sublist.
        	    for (int count = 1; count <= total; count++) {
            	    ArrayList<Integer> sublist = new ArrayList<Integer>(listMember);
            	    // Repeated inserting with the desired times
            	    for (int k = 1; k <= count; k++) {
            	        sublist.add(current);
            	    }
            		ret.add(sublist);    
        	    }
        	}
        	slow = fast;
        }
        return ret;
    }
}
