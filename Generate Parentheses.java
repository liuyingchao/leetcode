/*
 Problem:    Generate Parentheses
 Review this. The solution comes from aother coder!!!
 
 Notes:
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 For example, given n = 3, a solution set is:
 "((()))", "(()())", "(())()", "()(())", "()()()"

 Solution: Place n left '(' and n right ')'.
           Cannot place ')' if there are no enough matching '('.
 */
public class Solution {
	ArrayList<String> result;
    public ArrayList<String> generateParenthesis(int n) {
        result = new ArrayList<String>();
        helper(n, n, "");
        return result;
    }
    
    private void helper(int left, int right, String s) {
    	if (left == 0 && right == 0) {
    		result.add(s);
    	}
    	if (left > 0) {
    		helper(left - 1, right, s + "(");
    	}
    	if (right > left) {
    		helper(left, right - 1, s + ")");
    	}
    }
}
