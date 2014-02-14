/**
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?

Solution: The idiea is: push the root to stack first, then peek to see whether we can go to the left child;
 * then use prev to exam whether we have come back from the left child or the right child, so we can decide
 * to either push the right or output the current node.

*/
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
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
    	if (root != null) {
    		Stack<TreeNode> stack = new Stack<TreeNode>();
    		stack.push(root);
    		TreeNode prev = null;
    		while (!stack.isEmpty()) {
    			TreeNode node = stack.peek();
    			if (prev == node.left && node.left != null) { // We just visited the left child==>exam the right child
    			    if (node.right != null) {
    				    stack.push(node.right);
    				    prev = node.right;
    			    } else {
    			        list.add(node.val);
    			        prev = stack.pop();
    			    }
    			} else if (prev == node.right && node.right != null) { // We just visited the right child ==> visit the current node
			        list.add(node.val);
			        prev = stack.pop();
    			} else { // We just peek the current node for the first time before visiting either child
    			    if (node.left != null) { 	// Push the left child
    			        stack.push(node.left);
    			        prev = node.left;
    			    } else if (node.right != null) { // Left child is null==> push the right child
    			        stack.push(node.right);
    				    prev = node.right;
    			    } else {  	// Both left and right are null==> visit the current node and pop. Expect to visit the parent in the next step
    			        list.add(node.val);
    			        prev = stack.pop();
    			    }
    			}
    		}
    	}
    	return list;
    }
}
/*
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(root, list);
        return list;
    }
    
    private void helper(TreeNode root, ArrayList<Integer> list) {
        if (root != null) {
            helper(root.left, list);
            helper(root.right, list);
            list.add(root.val);
        }
    }
}
