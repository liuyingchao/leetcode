public class Solution {
    public void merge(int A[], int m, int B[], int n) {
    	for (int i = 0; i < m; i++) {
    		A[i+n] = A[i];
    	}
    	
    	int i = n, j = 0, k = 0;
    	
    	while (i < m+n && j < n) {
    		if (A[i] < B[j]) {
    			A[k++] = A[i++];
    		} else {
    			A[k++] = B[j++];
    		}
    	}
    	
    	if (i >= m + n) {
    		for (int r = j; r < n; r++) {
    			A[k++] = B[r++]; 
    		}
    	} else {
    		for (int r = i; r < m + n; r++) {
    			A[k++] = A[r++]; 
    		}a
    	}
    }
}
