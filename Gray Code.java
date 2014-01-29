/*
 * Look into the trick of how to "mirror"
 *
 * The idea is described at:
 * http://xiaotong-blog.herokuapp.com/posts/47
 *  
 * *  *  *  *  *  **  *  *  *  *  * *  *  *  *  *  * *  *  *  *  *  * *  *  *  *  *  * *  *  *  *  *  * *  *  *  *  *  *   
 * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * */
public class Solution {
   public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> ans=new ArrayList<Integer>();
        ans.add(0);
        int num=1;
        for(int i=1;i<=n;i++){
        	// Looping down to get the effect of "mirroring" on the latest result
            for(int j=ans.size()-1;j>=0;j--){
                ans.add(num+ans.get(j));
            }
            num<<=1;
        }
        return ans;
    }
}
