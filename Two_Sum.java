/* Two Sum--
 * Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        int N = numbers.length;
        int i = 0;
        int[] result = new int[2];
        for (i = 0; i < N; i++) {
        	int toFind = target - numbers[i];
        	if (hash.containsKey(toFind)) {
        		result[0] = hash.get(toFind) + 1;
        		result[1] = i + 1;
        		break;
        	} else {
        		if (!hash.containsKey(numbers[i])) {
        			hash.put(numbers[i], i);
        		}
        	}
        }
        return result;
    }
}
