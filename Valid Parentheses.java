import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
    	Stack<Character> stack = new Stack<Character>();
    	int N = s.length();
    	char c;
    	char top;
    	for (int i = 0; i < N; i++) {
    		c = s.charAt(i);
    		switch (c) {
    		case '(':
    		case '[':
    		case '{':
    			stack.push(c);
    			break;
    		case ')':
    		case ']':
    		case '}':
    			if (stack.isEmpty()) {
    				return false;
    			}
    			top = stack.pop();
    			if (!isPair(top, c)) {
    				return false;
    			}
    			break;
    		}
    	}
    	
    	return stack.isEmpty();
    }
    
    private boolean isPair(char left, char right) {
    	switch (left) {
    	case '(':
    		return right == ')';
    	case '[':
    		return right == ']';
    	case '{':
    		return right == '}';    		
    	}
    	return false;
    }
}
