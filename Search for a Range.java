/*
 * Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

Solution: first find a single match. Return [-1, -1] if no match. Otherwise, "grow" from the matching index
to both directions. Utilize the loop invariant of there is only == and one other alternative for the new binary search,
plus the most recent "start" and "end" are still a super set covering the valid range.
 * */
public class Solution {
    public int[] searchRange(int[] A, int target) {
    	int n = A.length;
    	int start = 0, end = n - 1, mid;
    	int matchIndex = -1;
    	while (start <= end) {
    		mid = start + (end-start)/2;
    		if (A[mid] == target) {
    			matchIndex = mid;
    			break;
    		} else if (A[mid] < target) {
    			start = mid + 1;
    		} else {
    			end = mid - 1;
    		}
    	}
    	
    	int[] result = new int[2];
    	if (matchIndex == -1) {
    		result[0] = result[1] = -1;
    		return result;
    	}
    	
    	// start and end still covers all the valid results.
    	int endBackup = end, midBackup = matchIndex;
    	
    	// Find first half
    	end = matchIndex;
    	while (start <= end) {
    		mid = start + (end-start)/2;
    		if (A[mid] < target) {
    			start = mid + 1;
    		} else { //A[mid] == target
    			end = mid - 1;
    		}
    	}
    	result[0] = start;
    	
    	start = midBackup;
    	end = endBackup;
    	while (start <= end) {
    		mid = start + (end-start)/2;
    		if (A[mid] > target) {
    			end = mid - 1;
    		} else { //A[mid] == target
    			start = mid + 1;
    		}
    	}
    	result[1] = end;
    	
    	return result;
    }
}
