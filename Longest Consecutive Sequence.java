/*
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Solution: use avaialable dataSet to track what numbers are in the array, then scan for the second
time by using a visited hashSet to assist "discovering" from a new number to both sides. By using
hashSet for both scans, we can simply ignore the scenario of having duplicate numbers. They are
automatically handled.

 * */
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
        // We can iterate the hashSet values directly without scanning the original array.
        // This may improve the performance, but there is no complexity improvement compared with the older version
        for (int n : dataSet) {
        	if (!visitedSet.contains(n)) {
        		visitedSet.add(n);
        		
        		int length = 1;
        		int current = n + 1;
        		while (dataSet.contains(current)) {
        			visitedSet.add(current);
        			current++;
        			length++;
        		}
        		current = n - 1;
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

// Older version
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
