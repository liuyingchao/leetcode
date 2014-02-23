/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

Difficulty: Hard

Solution:  The code may be refactored to be shorter
2 pointers. The idea is to parse T into a hashMap of char counts. Then we try to find the first substring in S
that covers T--this is the candidate of minWindow. If we find one, we move the slower pointer forward until we break the coverage.
At that point, we move the fast pointer to recover T. If we can't recover, we are done; otherwise, we've built a coverage
that is composed of the tail within S and some chars closer to slow pointer, which may or may not be useful for the coverage.
Then we move slow forward until we reach the point of breaking the coverage--that's the point we have another candidate
for minWindow.

In the example, after we break "ADOBEC", we rebuild a valid coverage of "DOBECODEBA" in the second inner while loop,
then we shrink it to "BECODEBA" in the first inner while loop, and break "CODEBA" again while "BE" is garbage data;
In the end, we build another coverage in "ODEBANC", and then trim the front garbage "ODE" to reach the winning candidate "BANC".
*/
public class Solution {
    public String minWindow(String S, String T) {
        HashMap<Character, Integer> target = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            if (target.containsKey(c)) {
                int val = target.get(c);
                target.put(c, val+1);
            } else {
                target.put(c, 1);
            }
        }
        int hashSize = target.size();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int n = S.length();
        int count = 0;
        int fast;
        boolean found = false;
        for (fast = 0; fast < n; fast++) {
            char c = S.charAt(fast);
            if (target.containsKey(c)) {
                int bar = target.get(c);
                int val = 0;
                if (map.containsKey(c)) {
                    val = map.get(c);
                }
                val++;
                map.put(c, val);
                if (val == bar) {
                    // We have an exactly coverage of one char in T map
                    count++;
                    if (count == hashSize) {
                        found = true;
                        break;
                    }    
                }
                
            }
        }
        
        if (!found) {
            return "";
        }
        
        int slow = 0;
        int min = fast - slow + 1;
        String candidate = S.substring(slow, fast+1);
        while (fast < n) {
            char tip = S.charAt(slow);
            while (count == hashSize) {
                char c = S.charAt(slow);
                if (target.containsKey(c)) {
                    int bar = target.get(c);
                    int val = map.get(c);
                    if (val == bar) {
                        // We decrease the count of how many chars in T are covered.
                        count--;
                        // This is the char we look for to rebuild the coverage
                        tip = c;
                        // This is also a candidate of minWindow before we break it!!!
                        int len = fast - slow + 1;
                        if (len < min) {
                            min = len;
                            candidate = S.substring(slow, fast+1);
                        }
                    }
                    
                    if (val == 1) {
                        map.remove(c);
                    } else {
                        map.put(c, val-1);
                    }
                }
                slow++;
            }
            fast++;
            while (fast < n) {
                char c = S.charAt(fast);
                if (target.containsKey(c)) {
                    int bar = target.get(c);
                    if (c == tip) {
                        count++;
                        map.put(c, bar);
                        break;
                    } else {
                        int val = map.get(c);
                        map.put(c, val+1);    
                    }
                }
                fast++;
            }
            // No need to compare with the candidate, because it's very likely we'll have
            // some garbage chars in this coverage. Having garbage or not, if fast < n, 
            // then we'll go back to the first inner loop to clean up and build candidate;
            // otherwise, we don't have a valid coverage in the tail==>just terminate the loop
        }
        return candidate;
    }
}
