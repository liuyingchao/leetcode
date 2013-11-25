/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // My Note: the code is not ideal, because I'm repeatedly scanning the tree top down, which is O(n^2)
public class Solution {
	public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
    	int depthL = getDepth(root.left);
    	int depthR = getDepth(root.right);
    	int gap = depthL - depthR;
    	if (gap > 1 || gap < -1) {
    		return false;
    	}
    	return isBalanced(root.left) && isBalanced(root.right);        
    }
    
    private int getDepth(TreeNode root) {
    	if (root == null) return 0;
    	return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }
}
