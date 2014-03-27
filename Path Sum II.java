/**
 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

Difficulty: Medium
Solution: the idea is to use a list to serve as the call stack, and use the copy constructor of
Java ArrayList to add a new copy in to the result list.
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null) return res;
        ArrayList<Integer> list = new ArrayList<Integer>();
        visit(root, sum, list, res);
        return res;
    }
    
    private void visit(TreeNode root, int rem, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> res) {
        list.add(root.val);
        if (root.left == null && root.right == null && rem == root.val) {
            res.add(new ArrayList<Integer>(list));
        }
        if (root.left != null) visit(root.left, rem - root.val, list, res);
        if (root.right != null) visit(root.right, rem - root.val, list, res);
        list.remove(list.size()-1);
    }
}
