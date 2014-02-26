/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

*/

public class Solution {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (k <= 0 || n < 1 || k > n)    return result;
        populateResult(1, n, 0, result, new int[k]);
        return result;
    }
    
    private void populateResult(int startNum, int endNum, int pos, ArrayList<ArrayList<Integer>> result, int[] path) {
        if (pos == path.length) {
            ArrayList<Integer> combination = new ArrayList<Integer>();
            for (int i : path) {
                combination.add(i);
            }
            result.add(combination);
            return;
        }
  
        for (int i = startNum; i <= endNum; i++) { //i is using which number
            path[pos] = i;
            populateResult(i + 1, endNum, pos + 1, result, path);//use next number on next position
            //as i++, use next number on same position
        }
    }
}
