/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

Difficulty: Hard
Solution: some sort of DP. The trickiest parts are: (1) we acknowledge the fact that we may have a prefix of invalid string,
then everything else after that point has nothing to do with those value, so we have valid f[x]==0; (2) For '(', we need to
track whether the previous char is ')', if so we need to copy f value to use in f[prev]. Safe to ignore if the previous is '('==>
that will be matched from line 24 to 28 when necessary.
*/
public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        int n = s.length();
        int max = 0;
        int[] f= new int[n];
        int len;
        for (int i = 1; i < n; i++) {
            len = 0;
            if (s.charAt(i) == ')') {
                if (s.charAt(i-1) == '(') {
                    len = 2;
                    if (i >= 2 && s.charAt(i-2) == ')') {
                        len += f[i-2];
                    }
                } else {
                    int prev = i - f[i-1] - 1;
                    if (prev >= 0 && s.charAt(prev) == '(') {
                        len = f[i-1] + 2 + f[prev];
                    }
                }
                f[i] = len;
                max = Math.max(len, max);
            } else { //s.charAt(i) == '('
                if (s.charAt(i-1) == ')') {
                    f[i] = f[i-1];
                }
            }
        }
        return max;
    }
}
