/* Notice the assumption of distinct elements. If there are duplicates, there is ambiguity of which one to return, and finding the first one would have to be O(n)
 * */
public class Solution {
    public int search(int[] A, int target) {
        int minIndex = findMinIndex(A);
        int N = A.length;
        if (minIndex == 0) {
        	return binarySearch(A, target, 0, N - 1);
        } else {
        	if (A[0] <= target) {
        		return binarySearch(A, target, 0, minIndex - 1);
        	} else {
        		return binarySearch(A, target, minIndex, N - 1);
        	}
        }
    }
    
    private int findMinIndex(int[] A) {
    	int N = A.length;
    	int start = 0, end = N - 1;
    	int mid;
    	while (start < end) {
    		mid = start + (end - start)/2;
    		if (A[mid] > A[end]) {
    			start = mid + 1;
    		} else {
    			end = mid;
    		}
    	}
    	return start;
    }
    
    private int binarySearch(int[] A, int target, int start, int end) {
    	int mid;
    	while (start <= end) {
    		mid = start + (end - start)/2;
    		if (A[mid] == target) {
    			return mid;
    		} else if (A[mid] > target) {
    			end = mid - 1;
    		} else {
    			start = mid + 1;
    		}
    	}
    	return -1;
    }
}
