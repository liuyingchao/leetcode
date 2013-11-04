public class Solution {
    public int singleNumber(int[] A) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int start = A[0];
        for (int i = 1; i < A.length; i++) {
            start ^= A[i];
        }
        return start;
    }
}
