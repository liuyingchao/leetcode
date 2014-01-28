/*You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * */
public class Solution {
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return n;
        int[] f = new int[n+1];
        // This can be refactored to use just 2 vars: prev and next. i can still serve as the loop counter.
        // I've done that refactor in the past. No need to bother for now.
        f[0] = 0; f[1] = 1; f[2] = 2;
        for (int i = 3; i <= n; i++) {
        	f[i] = f[i-1] + f[i-2];
        }
        return f[n];
    }
}
