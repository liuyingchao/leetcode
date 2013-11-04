/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) { return true;}
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if ((p == null && q != null) ||
            (p != null && q == null) ||
            (p.val != q.val)) {
                return false;
            }
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}
*/
// slightly better readability
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) { return true;}
        if (p == null || q == null) {
                return false;
            }
        return (p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}
