/* Adding another explanation in C++:
http://leetcodesolution.blogspot.com/2013/09/permutations-ii.html
*/

/* Solution II; with hash. NOT elegant

Very similar to original permutations without any duplicate elements. It just utilizes hash to avoid duplicate
 * */
import java.util.*;
public class Solution {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        if (num == null){
            return null;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        if (num.length == 0){
            return result; 
        }
        
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(num[0]);
        result.add(temp);
        
        for (int i = 1; i < num.length; i++){
            result = insert(num[i], result);
        }
        
        return result;
    }
    
    private ArrayList<ArrayList<Integer>> insert(int i, ArrayList<ArrayList<Integer>> result) {
        ArrayList<ArrayList<Integer>> newResult = new ArrayList<ArrayList<Integer>>();
        HashSet<ArrayList<Integer>> hash = new HashSet<ArrayList<Integer>>();
        
        for (ArrayList<Integer> current: result){
            for (int j=0; j <= current.size(); j++){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.addAll(current);
                temp.add(j, i);
                if (!hash.containsKey(temp)){
                    newResult.add(temp);
                    hash.add(temp);
                }
            }
        }
       return newResult;
    }
}
/*
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
 * 
 * Copied from : http://gongxuns.blogspot.com/2012/12/leetcodepermutations-ii.html
 * 
 * */
public class Solution {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        int i=num.length-1;
        res.add(convert(num));
        while(i>0){
            int j=i-1;
            while(j>=0 && num[j]>=num[j+1])
                j--;
            if(j>=0){
                i=num.length-1;
                for(int m=j;m<i-1;m++){
                    for(int k=i;k>m+1;k--){
                        if(num[k]<num[k-1]){
                            int temp=num[k];
                            num[k]=num[k-1];
                            num[k-1]=temp;
                        }
                    }
                }
                int m=j+1;
                while(num[m]<=num[j])
                    m++;
                int temp=num[m];
                num[m]=num[j];
                num[j]=temp;
                res.add(convert(num));
            }else{
                i--;
            }
        }
        return res;
    }
    
    public ArrayList<Integer> convert(int[] num){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=0;i<num.length;i++)
            res.add(num[i]);
        return res;
    }
}
