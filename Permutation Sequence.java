/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.

Difficulty: Medium

Solution: This really is a math problem plus a trick of using ArrayList for candidate removal for free.
*/
public class Solution {
    public String getPermutation(int n, int k) {
        ArrayList<Integer> numberList=new ArrayList<Integer>(); 
        // Prepare numberList and calculate factorial at the same time.
        int factorial = 1;
        for(int i=1; i<=n; i++)
        {
            numberList.add(i);
            factorial *=i;
        }
        
        StringBuffer sb=new StringBuffer(); 
        for(int i=n; i>0; i--)
        {
            factorial /= i;
            // Notice we -1 here because the requirement counts starting from 1
            int quotient = (k-1) /factorial;
            sb.append(numberList.get(quotient));
            
            // This is the HEART and SOUL of the problem! Removing the selection from numberList automatically leaves the
            // remaining numbers in clean state.
            // Java ArrayList removes by both index and Object. When the content of the list is also integer,
            // this operation is fucking confusing!!! Since we are passing in a primitive number instead of
            // an Integer object, it treats it as an index.
            numberList.remove(quotient);
            k -= quotient * factorial;
        }
        return sb.toString(); 
    }
}
