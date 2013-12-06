// This is the Dutch Flag Problem
public class Solution {
	private int zeroTail, twoHead;
    public void sortColors(int[] A) {
        if (A != null && A.length > 0) {
        	int N = A.length;
        	zeroTail = -1;
        	twoHead = N;
        	int current = 0;
        	while (current < twoHead) {
        		switch (A[current]) {
        		case 0:
        			zeroTail++;
        			swap(A, current, zeroTail);
        			current++;
        			break;
        		case 1:
        			current++;
        			break;
        		case 2:
        			twoHead--;
        			swap(A, current, twoHead);
        			break;
        		}
        	}
        }
    }
    
    private void swap(int[] A, int a, int b) {
    	int temp = A[a];
    	A[a] = A[b];
    	A[b] = temp;
    }
}
