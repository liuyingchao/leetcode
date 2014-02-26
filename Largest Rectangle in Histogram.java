/*
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10

Difficulty: Hard

Solution: Utilize stack

 * */
public class Solution {
    public int largestRectangleArea(int[] height) {
    	if (height == null || height.length == 0) return 0;
        Stack s = new Stack();
        int max = 0;
        int n = height.length;
        for (int i = 0; i < n; i++) {
        	if (s.isEmpty()) {
        		s.push(i);
        	} else {
        		while (!s.isEmpty() && height[(Integer) s.peek()] > height[i]) {
        			int top = (Integer) s.pop();
        			int leftIndex;
        			if (s.isEmpty()) {
        				max = Math.max(max, i * top);
        			} else {
        				leftIndex = (Integer) s.peek();
        				max = Math.max(max, (i-leftIndex-1) * top);
        			}        			
        		}
        		s.push(i);
        	}
        }
        
        while (!s.isEmpty()) {
        	int top = (Integer) s.pop();
        	if (s.isEmpty()) {
				max = Math.max(max, n * top);
			} else {
				int leftIndex = (Integer) s.peek();
				max = Math.max(max, (n-leftIndex-1) * top);
			}
        }
        
        return max;
    }
}
