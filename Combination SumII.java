/* 
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
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();    	
    	ArrayList<Integer> partial = new ArrayList<Integer>(); 
    	fillList(candidates, target, 0, partial, result);    	
    	return result;
    }
    
    private void fillList(int[] src, int target, int start, ArrayList<Integer> partial, ArrayList<ArrayList<Integer>> result) {
    	if (target == 0) {
    		ArrayList<Integer> element =  new  ArrayList<Integer>();
    		for (Integer current : partial) {
    			element.add(current);
    		}
    		result.add(element);
    		return;
    	}
    	
    	for (int i = start; i < src.length && target >= src[i]; i++) {
    		// if src[i] == src[i-1], by this point, we've found all the lists that start with src[i-1], so we can skip src[i]
    		if (i > start && src[i] == src[i-1]) continue;
    		partial.add(src[i]);
    		// Notice the 3rd parm is i+1, that's how we avoid duplicate uses of the same candidate element
    		fillList(src, target -  src[i], i+1, partial, result);
    		partial.remove(partial.size() - 1);
    	}
    }
}
