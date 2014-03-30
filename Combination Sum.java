/*Combination Sum Total Accepted: 3841 Total Submissions: 15041 My Submissions
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
 * */
import java.util.*;

public class Solution {

    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
    	HashSet<Integer> cSet =  new HashSet<Integer>();
    	for (int i = 0; i < candidates.length; i++) {
    		cSet.add(candidates[i]);
    	}
    	Integer[] src = new Integer[cSet.size()];
    	cSet.toArray(src);
    	Arrays.sort(src);
    	
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> partial = new ArrayList<Integer>(); 
    	fillList(src, target, 0, partial, result);
    	return result;
    }
    
    private void fillList(Integer[] src, int target, int start, ArrayList<Integer> partial, ArrayList<ArrayList<Integer>> result) {
    	if (target == 0) {
    		ArrayList<Integer> element =  new  ArrayList<Integer>(partial);
    		result.add(element);
    		return;
    	}
    	
    	for (int i = start; i < src.length && target >= src[i]; i++) {
    		partial.add(src[i]);
    		fillList(src, target -  src[i], i, partial, result);
    		partial.remove(partial.size() - 1);
    	}
    }
}
