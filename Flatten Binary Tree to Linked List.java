/**
 * Solution: the idea is to flatten the left, insert it as the new right, and traverse to the "tail" of the partial flatten list, then insert the original right
 * as the new right, at last faltten the right side recursively. Remember to set left to null as necessary.
 * 
 * Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
 * 
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) { return; }
        
        if (root.left == null) {
        	flatten(root.right);
        } else {
        	flatten(root.left);
        	TreeNode temp = root.right;
        	root.right = root.left;
        	root.left = null;
        	TreeNode current = root.right;
        	while (current.right != null) {
        		current = current.right;
        	}
        	current.right = temp;
        	flatten(current.right);
        }
    }
}
