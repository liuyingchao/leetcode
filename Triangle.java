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
