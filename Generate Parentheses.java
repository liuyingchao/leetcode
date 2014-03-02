/*
 Problem:    Generate Parentheses
 Review this. The solution comes from aother coder!!!
 
 Notes:
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 For example, given n = 3, a solution set is:
 "((()))", "(()())", "(())()", "()(())", "()()()"

 Solution: Place n left '(' and n right ')'.
           Cannot place ')' if there are no enough matching '('.
 
 The idea is to keep track of how many left and right chars we still allow to have. When both are 0,
 we know we have a valid sequence. If not, we can insert left as long as we don't run out of n; and
 we can also insert right as long as it's no more than left. Hence the 2 if conditions in the helper.
   
 */
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
