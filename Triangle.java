/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

Difficulty: Easy

Solution: DP. Notice this pattern of using 2 lists to get O(n) space usage, which tends to point current to prev at the
end of the loop body; and the subtlety of having to do that in the beginning of the loop to handle the input of just 1 by 1--
that's not a concern with O(n^2) space usage.
*/
public class Solution {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        ArrayList<Integer> prev = new ArrayList<Integer>();
        int n = triangle.size();
        prev.add(triangle.get(0).get(0));
        // In case there is only one element
        ArrayList<Integer> current = prev;
        for (int i = 1; i < n; i++) {
            ArrayList<Integer> list = triangle.get(i);
            current = new ArrayList<Integer>();
            // The first element in the row
            current.add(list.get(0) + prev.get(0));
            // The elements in the middle
            for (int j = 1; j < i; j++) {
                current.add(list.get(j) + Math.min(prev.get(j-1), prev.get(j)));
            }
            // The last element in the row
            current.add(list.get(i) + prev.get(i-1));
            // Point prev to current to prepare for the next level
            prev = current;
        }
        int min = Integer.MAX_VALUE;
        for (Integer val : current) {
            if (val < min) min = val;
        }
        return min;
    }
}


//O(n^2) space usage
public class Solution {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<ArrayList<Integer>> table = new ArrayList<ArrayList<Integer>>();
        int n = triangle.size();
        ArrayList<Integer> top = new ArrayList<Integer>();
        top.add(triangle.get(0).get(0));
        table.add(top);
        for (int i = 1; i < n; i++) {
            ArrayList<Integer> currentLevel = triangle.get(i);
            ArrayList<Integer> prevTableLevel = table.get(i-1);
            ArrayList<Integer> currentTableLevel = new ArrayList<Integer>();
            for (int j = 0; j < currentLevel.size(); j++) {
                if (j == 0) {
                    currentTableLevel.add(currentLevel.get(0) + prevTableLevel.get(0));
                } else if (j == currentLevel.size() - 1) {
                    currentTableLevel.add(currentLevel.get(j) + prevTableLevel.get(j-1));
                } else {
                    currentTableLevel.add(currentLevel.get(j) + Math.min(prevTableLevel.get(j-1), prevTableLevel.get(j)));
                }
            }
            table.add(currentTableLevel);
        }
        int min = Integer.MAX_VALUE;
        for (Integer val : table.get(n-1)) {
            if (val < min) min = val;
        }
        return min;
    }
}
