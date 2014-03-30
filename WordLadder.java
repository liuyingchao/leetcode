/*
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

Difficulty: Hard

Solution: We shouldn't build the complete graphy by pre-processing the dictionary, as that could easily time out on strings that
are absolutely not part of the solution. Be careful with how to avoid repeatedly putting the same string into the queue, when to do len+1,
and when to check matching result. BFS is natural for shortest distance.
*/

public class Solution {
    class Pair {
        public String word;
        public int len;     // How many steps to reachh this word from start
        public Pair(String word, int len) {
            this.word = word;
            this.len = len;
        }
    }
    public int ladderLength(String start, String end, HashSet<String> dict) {
        if (start == null || end == null || start.length() != end.length()) return 0;
        if (start.equals(end)) return 1;
        int n = start.length();
        
        // Initialize the queue
    	Queue<Pair> q = new LinkedList<Pair>();
    	q.add(new Pair(start, 1));
    	
    	// IMPORTANT: keep track of what strings have been examed. This is important to ensure we don't 
    	// fall into infinite loop by keeping increasing the cound of the same strings
    	HashSet<String> found = new HashSet<String>();
    	found.add(start);
    	
    	// BFS
    	Pair front;
    	String current;
    	while (!q.isEmpty()) {
    		front = q.poll();
    		current = front.word;
    		
    		for (int i = 0; i < n; i++) {
    		    // IMPORTANT: be careful with declaring the array at this level instead of before the outerloop!!
    		    char[] arr = current.toCharArray();
    		    for (char c = 'a'; c <= 'z'; c++) {
                    arr[i] = c;
                    String s = String.valueOf(arr);
                    // Must check here instead of outside the double loop, because end may not be in the dictionary.
                    // If we check outside the outer loop, we'll have to add end into the dict, which may not be acceptable.
                    if (s.equals(end)) {
                        return front.len + 1;
                    }
                    if (dict.contains(s) && !found.contains(s)) {
                        q.add(new Pair(s, front.len + 1));
                        // Add into found so that we won't put this string into the queue again.
                        found.add(s);
                    }
                }
    		}
    	}
    	return 0;
    }
}
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
