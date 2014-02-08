/*
 * Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

Solution: induction. For each index i of the num array, we insert it into all the i positions of the existing permutations.
Notice we start with index 0, so "i" is really conceptually more like "i+1" in the traditional induction. 
 * */
public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        int n = num.length;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> first = new ArrayList<Integer>();
    	// Assume n >= 1. Otherwise, it's unclear what the requirement should return
        first.add(num[0]);
        res.add(first);
        for (int i = 1; i < n; i++) {
        	// Point a backup pointer to the current result
            ArrayList<ArrayList<Integer>> currentList = res;
            // Then reference the res pointer to a new value so that we don't mess up references
            res = new ArrayList<ArrayList<Integer>>();
            for (ArrayList<Integer> current : currentList) {
            	// Notice j <= i. For the current index i, we already have the permutation of length i.
            	// We need to insert into i+1 positions to generate new permutation.
                for (int j = 0; j <= i; j++) {
                    current.add(j, num[i]);
                    // Ugly way to clone. Java ArrayList clone() method seems to return total garbage.
                    ArrayList<Integer> newResult = new ArrayList<Integer>();
                    for (Integer currentVal : current) {
                        newResult.add(currentVal);
                    }
                    res.add(newResult);
                    current.remove(j);
                }
            }
        }
        return res;
    }
}
