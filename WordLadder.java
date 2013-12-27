import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// This version times out on big input
public class Solution {
    public int ladderLength(String start, String end, HashSet<String> dict) {
    	HashSet<String> currentSet;
    	HashMap<String, HashSet<String>> graphMap = new HashMap<String, HashSet<String>>();
    	// Build the graph
    	for (String word : dict) {
    		currentSet = new HashSet<String>();
			for (String val : dict) {
    			if (canWalk(word, val)) {
    				currentSet.add(val);
    			}
    		}
			// Link the target
			if (canWalk(word, end)) {
				currentSet.add(end);
			}
			graphMap.put(word, currentSet);
    	}

    	String separator =  start.concat(end);
    	// Initialize the queue
    	Queue<String> q = new LinkedList<String>();

    	for (String word : dict) {
    		 if (canWalk(start, word)) {
    			 q.add(word);
    		 }
    	}
    	q.add(separator);
    	
    	// BFS
    	HashSet<String> visited =  new HashSet<String>();    	
    	int step = 1;
    	String current;
    	while (!q.isEmpty()) {
    		current = q.poll();
    		if (current.equals(separator)) {
    			step++;
    		} else if (current.equals(end)) {
    			return step;
    		} else {
    			currentSet = graphMap.get(current);
    			for (String word: currentSet) {
    				if (!visited.contains(word)) {
    					q.add(word);
    					visited.add(word);
    				}
    			}
    			q.add(separator);
    		}
    	}
    	return 0;
    }
    
    private boolean canWalk(String s1, String s2) {
    	if (s1.equals(s2)) {
    		return false;
    	}
    	int N = s1.length();
    	int diff = 0;
    	for (int i = 0; i < N; i++) {
    		if (s1.charAt(i) != s2.charAt(i)) {
    			diff++;
    			if (diff > 1) {
    				return false;
    			}
    		}
    	}
    	
    	return diff == 1;
    }
}
