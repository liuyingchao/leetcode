/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

Difficulty: Easy

Solution: DP with O(n^2) solution. 
*/
public class Solution {
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) return false;
        int n = A.length;
        boolean[] flag = new boolean[n];
        flag[0] = true;
        for (int i = 1; i < n; i++) {
            // flag[i] = false;
            for (int j = i-1; j >= 0; j--) {
                if (flag[j] && A[j] >= i-j) {
                    flag[i] = true;
                    break;
                }
            }
        }
        return flag[n-1];
    }
}
