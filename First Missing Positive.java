/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

Solution: the idea is to use the fact that an array of elements naturally gives us
a place to "bitmark" which number exists in the array. However, this solution uses O(n) space

*/
public class Solution {
    public int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0) return 1;
        int n = A.length;
        int[] mark = new int[n];
        for (int i = 0; i < n; i++) {
            mark[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            if (A[i] > 0 && A[i] <= n) {
                mark[A[i]-1] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (mark[i] < 0) {
                return i+1;
            }
        }
        return n+1;
    }
}
