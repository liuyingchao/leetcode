public class Solution {
    public int reverse(int x) {
        //$ TODO: I'm not handling the overflow correctly.
        int sum = 0;
        boolean negative = false;
        if (x < 0) {
            x = x * (-1);
            negative = true;
        }
        while (x > 0) {
            int d = x % 10;
            sum = sum * 10 + d;
            x = x / 10;
        }
        if (negative) {
            sum *= -1;
        }
        return sum;
    }
}
