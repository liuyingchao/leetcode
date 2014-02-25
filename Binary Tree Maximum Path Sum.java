/**
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
 * 
 * Difficulty: Medium
 * 
 * Solution: the idea is to capture what's the largest possible sum from left and right children,
 * then combining that into the root value to have 4 candidates for max. However, only one of the
 * left or right or the root value itself can be returned to parent--that's what singleCandidate for.
 * We update max as we traverse, but not necessarily returning it in the helper getMaxEndingAtRoot() function.
 * 
 */
public class Solution {
    private int max;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        max = Integer.MIN_VALUE;
        getMaxEndingAtRoot(root);
        return max;
    }
    
    private int getMaxEndingAtRoot(TreeNode root) {
        int singleCandidate = root.val;
        int leftSum = 0, rightSum = 0;
        if (root.left != null) {
            leftSum = getMaxEndingAtRoot(root.left);
            singleCandidate = Math.max(singleCandidate, leftSum + root.val);
        }
        if (root.right != null) {
            rightSum = getMaxEndingAtRoot(root.right);
            singleCandidate = Math.max(singleCandidate, rightSum + root.val);
        }
        
        int currentSum = Math.max(Math.max(root.val, root.val + leftSum + rightSum), 
                                    Math.max(root.val + leftSum, root.val + rightSum));
        if (currentSum > max) {
            max = currentSum;
        }
        
        return singleCandidate;
    }
}
