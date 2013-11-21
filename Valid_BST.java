import java.util.ArrayList;

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
	private int prev;
	public boolean isValidBST(TreeNode root) {
		this.prev = Integer.MIN_VALUE;
		if (root == null) return true;
		return isValid(root);
    }
	
	private boolean isValid(TreeNode root) {
		if (root == null) return true;
		
		// Notice the order of || is VERY important, because isValid() update prev internally 
		if (!isValid(root.left) || root.val <= prev) return false;
		
		prev = root.val;
		return isValid(root.right);	
	}
}
