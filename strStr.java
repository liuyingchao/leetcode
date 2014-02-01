/*
 * Implement strStr().

Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.


Solution: the testing code seems to be wrong. It's ok to return the starting index till the end instead of a substring
of needle's length, but line 15 to make it return "a" to ("a", "") simply is wrong.
 * */

public class Solution {
    public String strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) return haystack;
        int i = 0;
        while (i <= m-n) {
        	int j = 0;
        	while (j < n && haystack.charAt(i+j) == needle.charAt(j)) {
        		j++;
        	}
        	if (j == n) return haystack.substring(i);
        	i++;
        }
        return null;
    }
}
