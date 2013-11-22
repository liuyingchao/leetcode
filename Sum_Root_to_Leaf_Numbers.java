/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	private int sum;
    public int sumNumbers(TreeNode root) {
        sum = 0;
        if (root != null) {
        	getSum(root, 0);
        }
        return sum;
    }
    
    private void getSum(TreeNode root, int parentValue) {
    	int myValue = getCurrentValue(root, parentValue);
    	if (root.left == null && root.right == null) {
    		sum += myValue;
    		return;
    	}
    	if (root.left != null) {
    		getSum(root.left, myValue);
    	}
    	if (root.right != null) {
    		getSum(root.right, myValue);
    	}
    }
    
    private int getCurrentValue(TreeNode root, int parentValue) {
    	// It doesn't matter what lever we are, we can safely time parentValue by 10
    	return root.val + parentValue * 10;
    }
}
