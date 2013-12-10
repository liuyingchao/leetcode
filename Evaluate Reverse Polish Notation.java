import java.util.*;
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack stack =  new Stack();
        int operand1, operand2, current = 0;
        for (String s : tokens) {
        	try {
        		current = Integer.parseInt(s);
        		stack.push(current);
        	} catch (NumberFormatException ex) {
        		operand2 = (Integer) stack.pop();
    			operand1 = (Integer) stack.pop();
        		if (s.equals("+")) {
            		current = operand1 + operand2;  
            	} else if (s.equals("-")) {
            		current = operand1 - operand2;
            	} else if (s.equals("*")) {
            		current = operand1 * operand2;
            	} else if (s.equals("/")) {
            		current = operand1 / operand2;
            	}
        		stack.push(current);
        	}
        }
        return current;
    }
}
