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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
        	return false;
        }
        int remaining = sum - root.val;
        if (root.left == null && root.right == null) {
        	return remaining == 0;
        } else {
        	return hasPathSum(root.left , remaining) || hasPathSum(root.right, remaining);
        }    	
    }
}
