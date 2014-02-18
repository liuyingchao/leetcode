/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.

Solution: for the space contained by 2 pointers, the water amount is determined by the smaller of the 2 heights.
The key observation is when we move the smaller pointer, we won't miss a solution if the next pointer is higher
than the current shorter board. This relies on the inequation of
1*(n-1) < 2*(n-2) 
This is the extreme case of the lower board with height of 1. The right side will be even bigger if the shorter
board is higher than the shorter board of the left side.
*/
public class Solution {
    public int maxArea(int[] height) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (height == null || height.length == 0 || height.length == 1) return 0;
        int n = height.length;
        int front = 0, back = n - 1;
        int max = 0;
        int area;
        while (front < back) {
            boolean forward = height[front] <= height[back];
            area = (back - front) * (forward ? height[front] : height[back]);
            if (area > max) {
                max = area;
            }
            if (forward) {
                front++;
            } else {
                back--;
            }
        }
        
        return max;
    }
}
