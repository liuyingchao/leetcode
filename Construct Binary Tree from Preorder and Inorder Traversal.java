
/**
* Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0) return null;
        int N = preorder.length;
        return buildHelper(preorder, 0, N-1, inorder, 0, N-1);
    }
    
    private TreeNode buildHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
    	if (preStart > preEnd) return null;
    	TreeNode root = new TreeNode(preorder[preStart]);
    	int inorderRootIndex = findInorderRoot(inorder, inStart, inEnd, preorder[preStart]);
    	int leftLength = inorderRootIndex - inStart;
    	root.left = buildHelper(preorder, preStart + 1, preStart + leftLength, inorder, inStart, inorderRootIndex  - 1);
    	root.right = buildHelper(preorder, preStart + leftLength + 1, preEnd, inorder, inorderRootIndex + 1, inEnd);
    	return root;
    }
    
    private int findInorderRoot(int[] inorder, int inStart, int inEnd, int rootVal) {
    	for (int i = inStart; i <= inEnd; i++) {
    		if (inorder[i] == rootVal) {
    			return i;
    		}
    	}
    	return -1;
    }
}
