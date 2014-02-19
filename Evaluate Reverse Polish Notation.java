/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

Difficulty: Easy
  
Solution: string comparion is little more verbose than try catch on NumberFormatException,
but it's bad code practice to use Exception Handling for logic flow, and it performes worse.
So I rewrite to this verbose version.
*/

import java.util.*;
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack stack =  new Stack();
        int operand1, operand2, current = 0;
        char operator;
        for (String s : tokens) {
            if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")) {
                current = Integer.parseInt(s);
        		stack.push(current);
            } else {
        		operand2 = (Integer) stack.pop();
    			operand1 = (Integer) stack.pop();
    			operator = s.charAt(0);
    			switch (operator) {
					case '+':
					current = operand1 + operand2;
					break;
					case '-':
					current = operand1 - operand2;
					break;
					case '*':
					current = operand1 * operand2;
					break;
					case '/':
					current = operand1 / operand2;
					break;
    			}
    			
    			stack.push(current);
        	}
        }
        return current;
    }
}
