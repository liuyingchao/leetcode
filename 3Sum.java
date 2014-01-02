import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        // My analysis: similar to 2 sum, start 2 pointers from both ends. For each pair a and c, we binary 
        // search for 0-a-c between them. This seems to be O(n^2longn)
    	Arrays.sort(num);
    	ArrayList<ArrayList<Integer>> result =  new ArrayList<ArrayList<Integer>>(); 
    	int N = num.length;
    	int a, b, c, sum;
    	int i = 0;
    	while (i < N - 2) {
    		a = num[i];
    		int j = i + 1;
    		int k = N - 1;
    		while (j < k) {
    			b = num[j];
    			c = num[k];
    			// We can use another var target = 0 - num[i] outside this level of loop.
    			// That doesn't feel like better read
    			sum = a + b + c;
    			if (sum == 0) {
    				ArrayList<Integer> current = new ArrayList<Integer>();
    				current.add(a);
					current.add(b);
					current.add(c);
					result.add(current);
					j++;
					k--;
					while (j < k && num[j-1] == num[j]) {
						j++;
					}
					// Theoretically, we don't need this loop for correctness, but this simple check to avoid
					// evaluating the same c performes better than relying on the next loop of the same number c.
					while (j < k && num[k+1] == num[k]) {
						k--;
					}
    			} else if (sum > 0) {
					k--;
				} else {
					j++;
				}
    		}
    		while (i < N - 3 && num[i] == num[i+1]) {
    			i++;
    		}
    		i++;
    	}
    	
    	return result;
    }
}
