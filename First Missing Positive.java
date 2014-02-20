/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

Difficulty: Medium

Solution: reuse A for bookkeeping. This uses constant space, and appears linear time.
However, I'm not fully convinced that the if branch can be finished in constant time.
Some special inputs might yield O(n^2) solution.
*/

public class Solution {
    public int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0) return 1;
        int n = A.length;
        int i = 0;
        while (i < n) {
        	if (A[i] != i+1 && A[i] > 0 && A[i] <= n && A[A[i]-1] != A[i]) {
        		swap(A, i, A[i]-1);
        	} else {
        		i++;
        	}
        }
        
        for (i = 0; i < n; i++) {
        	if (A[i] != i+1) return i+1;
        }
        return n+1;
    }
    
    private void swap(int[] A, int i, int j) {
    	int temp = A[i];
    	A[i] = A[j];
    	A[j] = temp;
    }
}

/*
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
