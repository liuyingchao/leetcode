/* 
MY NOTE: look deep into it. Recursion and Combination
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
 * */
 
public class Solution {
    public ArrayList<ArrayList<String>> partition(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
        int length=s.length();
        if(length==0) return list; 
        part(s, 0, new ArrayList<String>(), list);
        return list; 
    }
    
    void part(String s, int start, ArrayList<String> subList, ArrayList<ArrayList<String>> list)
    {
        if(start==s.length()) 
        {
            // The key is this line that constructs a search result by copying
            // from the current subList. Then the recursion caller makes sure
            // the last element is removed before moving forward
            list.add(new ArrayList<String> (subList) ); return ;
        }
        for(int i=start+1; i<=s.length(); i++)
        {
            if(palindrome(s, start, i-1))
            {
                subList.add(s.substring(start, i));
                part(s, i , subList, list);
                subList.remove(subList.size()-1);
            }
        }
    }
    
    boolean palindrome(String s, int i, int j)
    {
        while(i<j)
        {
            if(s.charAt(i)!=s.charAt(j)) return false; 
            i++; 
            j--; 
        }
        return true; 
    }
}
