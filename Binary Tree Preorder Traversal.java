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
 
 // Iteratively
 public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if (root != null) {
    		Stack<TreeNode> stack = new Stack<TreeNode>();
    		stack.push(root);
    		while (!stack.isEmpty()) {
    			TreeNode node = stack.pop();
    			list.add(node.val);
    			if (node.right != null) {
    				stack.push(node.right);
    			}
    			if (node.left != null) {
    				stack.push(node.left);
    			}
    		}
    	}
    	return list;
    }
}

// Recursion
 
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
