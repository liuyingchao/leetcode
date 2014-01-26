/*
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 * */
import java.util.*;
public class Solution {
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) return 0;
        int N = num.length;
        
        // Scan to mark what data is available
        HashSet<Integer> dataSet = new HashSet<Integer>();
        for (int i = 0; i < N; i++) {
        	dataSet.add(num[i]);
        }
        
        // Store what data has been visited. This is the key to keep the amortized cost O(n)
        HashSet<Integer> visitedSet = new HashSet<Integer>();
        int maxLength = 0;
        for (int i = 0; i < N; i++) {
        	if (!visitedSet.contains(num[i])) {
        		visitedSet.add(num[i]);
        		
        		int length = 1;
        		int current = num[i] + 1;
        		while (dataSet.contains(current)) {
        			visitedSet.add(current);
        			current++;
        			length++;
        		}
        		current = num[i] - 1;
        		while (dataSet.contains(current)) {
        			visitedSet.add(current);
        			current--;
        			length++;
        		}
        		
        		if (length > maxLength) {
        			maxLength = length;
        		}
        	}
        }
        
        return maxLength;
    }
}
