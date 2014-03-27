/*
 Problem:    Generate Parentheses
 Review this. The solution comes from aother coder!!!
 It's also the same as Cracking Coding Interview Problem 9.6
 
 Notes:
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 For example, given n = 3, a solution set is:
 "((()))", "(()())", "(())()", "()(())", "()()()"

Difficulty: Medium

 Solution: Place n left '(' and n right ')'.
           Cannot place ')' if there are no enough matching '('.
 
 The idea is to keep track of how many left and right chars we still allow to have. When both are 0,
 we know we have a valid sequence. If not, we can insert left as long as we don't run out of n; and
 we can also insert right as long as it's no more than left. Hence the 2 if conditions in the helper.
   
 */
 
public class Solution {
	// Use how many we have instead of how many more we can insert to be more straightforward
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<String>();
        if (n == 0) return list;
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        char[] cA = new char[2*n];
        process(n, list, cA, 0, 0);
        return list;
    }
    
    private void process(int n, List<String> list, char[] cA, int left, int right) {
        if (right == n) {   // don't need to check against left, because we never try to go beyond n for left at the first place
            list.add(String.valueOf(cA));
            return;
        }
        if (right < left) {
            cA[left+right] = ')';
            process(n, list, cA, left, right+1);
        }
        if (left < n) {
            cA[left + right] = '(';
            process(n, list, cA, left+1, right);
        }
    }
} 
public class Solution {
	ArrayList<String> result;
    public ArrayList<String> generateParenthesis(int n) {
        result = new ArrayList<String>();
        helper(n, n, "");
        return result;
    }
    
    // left means how many left char we can have
    // right means how many right char we can have
    // s is the current string we've built
    private void helper(int left, int right, String s) {
    	if (left == 0 && right == 0) {
    		result.add(s);
    	}
    	if (left > 0) {	// We can still insert some "("
    		helper(left - 1, right, s + "(");
    	}
    	if (right > left) {	// We can sitll insert as many right "as" as we want.
    		helper(left, right - 1, s + ")");
    	}
    }
}
