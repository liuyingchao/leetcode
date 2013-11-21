import java.util.ArrayList;

//$ TODO: bug--returns false for {0, -1}
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
	private int prev = Integer.MIN_VALUE;
	public boolean isValidBST(TreeNode root) {
		if (root == null) return true;
		return isValid(root);
    }
	
	private boolean isValid(TreeNode root) {
		if (root == null) return true;
		
		if (root.left != null && !isValid(root.left)) {
			return false;
		}
		if (root.val <= prev) return false;
		
		prev = root.val;
		
		if (root.right != null && !isValid(root.left)) {
			return false;
		}
		return true;	
	}
}
