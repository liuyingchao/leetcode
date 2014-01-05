/**

Binary Tree Preorder Traversal Total Accepted: 5739 Total Submissions: 17038 My Submissions
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	traverse(root, list);
    	return list;
    }
    
    private void traverse(TreeNode root, ArrayList<Integer> list) {
    	if (root == null) {
    		return;
    	} else {
    		list.add(root.val);
    		traverse(root.left, list);
    		traverse(root.right, list);
    	}
    }
}
