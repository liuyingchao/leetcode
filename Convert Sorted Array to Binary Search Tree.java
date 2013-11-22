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
    public TreeNode sortedArrayToBST(int[] num) {
    	int N = num.length;
    	if (N == 0) {
    		return null;
    	}
    	return getTreeRoot(num, 0, N-1);
    }
    
    private TreeNode getTreeRoot(int[] num, int lower, int upper) {
    	if (lower > upper) {
    		return null;
    	}
    	int mid = (lower + upper)/2;
    	TreeNode root = new TreeNode(num[mid]);
    	root.left = getTreeRoot(num, lower, mid-1);
    	root.right = getTreeRoot(num, mid+1, upper);
    	return root;
    }
}
