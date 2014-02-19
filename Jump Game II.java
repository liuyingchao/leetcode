/* IMPORTANT NOTE in solution:

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Difficulty: Medium

Solution: straightforward DP. However, there is a trick that often applies for DP problems: line 37 checks whether
we've reached a best state that deems the remaining calculation unnecessary. This optimization trick applies
whenever we are looking for max/min of something.

*/
public class Solution {
    public int jump(int[] A) {
        // validation
        int n = A.length;
        int[] steps = new int[n];
        steps[0] = 0;
        for (int i = 1; i < n; i++) {
            steps[i] = -1;
        }
        
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[j] >= 0 && A[j] >= i-j) {
                    int count = steps[j] + 1;
                    if (count < min) {
                        min = count;
                    }
                    // This is the trick to avoid redundant calculation
                    if (min == 1) {
                        break;
                    }
                }
            }
            if (min != Integer.MAX_VALUE) {
                steps[i] = min;
            }
        }
        
        if (steps[n-1] >= 0) {
            return steps[n-1];
        } else {
            return -1;
        }
    }
}
