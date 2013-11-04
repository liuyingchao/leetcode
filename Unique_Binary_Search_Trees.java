public class Solution {
    public int numTrees(int n) {
        // The idea is to take one of the numbers i to be the root, then the number of valid BSTs
        // with this node being root is f(i-1)*f(n-i), because there are f(i-1) BSTs as its left child,
        // and f(n-i) as its right child. f(0)=1, because it corresponds to a null child. 
        // So the total of f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-1)*f(0) = sigma(f(i)*f(n-1-i), i from 0 to n-1).
        if (n <= 1) { return 1;}
        int[] f = new int[n+1];
        f[0] = f[1] = 1;
        f[2] = 2;
        for (int i = 3; i <= n; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += f[j]*f[i-1-j];
            }
            f[i] = sum;
        }
        return f[n];
    }
}
