/*
Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.

Difficulty: easy
Solution: 2 pointers plus hash
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int fast = 0, slow = 0, max = -1;
        while (fast < s.length()) {
            char c = s.charAt(fast);
            if (map.containsKey(c)) {
                int newStart = map.get(c) + 1;
                for (int i = slow; i < newStart; i++) {
                    map.remove(s.charAt(i));
                }
                slow = newStart;
            } else {
                max = Math.max(fast - slow + 1, max);
            }
            map.put(c, fast);
            fast++;
        }
        return max;
    }
}
