/* MY NOTE: the judge expects input [1,1], 1 to return [[1]], instead of [[1], [1]]. That means it's very inconsistent on what "duplicate"
 * means. On one hand, the candidates are not unique, on the other hand, those elements of the same values are treated differently. Quite odd requirement.
 * Otherwise, we can use ArrayList<ArrayList<Integer>> as the last parm of fillList without having to introduce a hashSet.
 * 
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6]  
 * */
import java.util.*;

public class Solution {
	
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] candidates, int target) {
    	Arrays.sort(candidates);
    	boolean[] mask = new boolean[candidates.length];
    	HashSet<ArrayList<Integer>> resultSet = new HashSet<ArrayList<Integer>>(); 
    	
    	ArrayList<Integer> partial = new ArrayList<Integer>(); 
    	fillList(candidates, mask, target, 0, partial, resultSet);
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    	for (ArrayList<Integer> list : resultSet) {
    		result.add(list);
    	}
    	return result;
    }
    
    private void fillList(int[] src, boolean[] mask, int target, int start, ArrayList<Integer> partial, HashSet<ArrayList<Integer>> resultSet) {
    	if (target == 0) {
    		ArrayList<Integer> element =  new  ArrayList<Integer>();
    		for (Integer current : partial) {
    			element.add(current);
    		}
    		resultSet.add(element);
    		return;
    	}
    	
    	for (int i = start; i < src.length && target >= src[i]; i++) {
    		if (!mask[i]) {
    			partial.add(src[i]);
    			mask[i] = true;
        		fillList(src, mask, target -  src[i], i, partial, resultSet);
        		partial.remove(partial.size() - 1);
        		mask[i] = false;
    		}
    	}
    }
}
