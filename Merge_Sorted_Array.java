public class Solution {
    //$ TODO: the following code is wrong, because I missed the assumption about A's original allocation.
    // Will add correct code.
    public void merge(int A[], int m, int B[], int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int[] C = A.clone();
        A = new int[m+n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
        	if (C[i] < B[j]) {
        		A[k++] = C[i++];
        	} else {
        		A[k++] = B[j++];
        	}
        }
        
        if (i >= m) {
        	for (int r = j; r < n; r++) {
        		A[k++] = B[r++];
        	}
        } else {
        	for (int r = i; r < m; r++) {
        		A[k++] = C[r++];
        	}
        }
    }
}
