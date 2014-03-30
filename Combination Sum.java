/*
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 

Difficulty : Medium -- However, when I tried this problem for the second time, I got tired and couldn't articulate fast even though I thought about the key constraint of target >= src[i]
Solution: Backtracking with a greedy strategy. Fill in the small candidates as many as possible. Use the constraint of target >= src[i] to terminate the loop and naturally terminate 
the recursion by reaching the end of recursion function.

 * */
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
    	
    	// Loop on start to indicate for the recursive callee, that's the "smallest" candidate among the remaining bigger elements
    	for (int i = start; i < src.length && target >= src[i]; i++) {
    		partial.add(src[i]);
    		// Comparred with Combination Sum II, the key difference is the 3rd parm of start. When we
    		// pass in i, we allow duplicates to fit in as many times as possible; when we pass in i+1,
    		// we naturally skip the current element to avoid duplicate
    		fillList(src, target -  src[i], i, partial, result);
    		partial.remove(partial.size() - 1);
    	}
    }
}
