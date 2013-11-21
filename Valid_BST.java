import java.util.ArrayList;
//$ TODO: the first solution exceeds memeory limit. The second is wrong for {1, 1}, which should return false.
// Still debugging.
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
 * public class Solution {
	public boolean isValidBST(TreeNode root) {
		ArrayList<Integer> al =  new ArrayList<Integer>();
		serielizeTree(root, al);
		int N = al.size();
		if (N <= 1) {
			return true;
		}
		for (int i = 0; i < N - 1; i++) {
			if (al.get(i) >= al.get(i+1)) {
				return false;
			}
		}
		return true;
    }
	
	private void serielizeTree(TreeNode root, ArrayList<Integer> al) {
		if (root != null) {
			serielizeTree(root.left, al);
			al.add(root.val);
			serielizeTree(root.left, al);
		}
	}
}
 * */
public class Solution {
	public boolean isValidBST(TreeNode root) {
		if (root == null) return true;
		return isValid(root, Integer.MIN_VALUE);
    }
	
	private boolean isValid(TreeNode root, int prev) {
		boolean valid = true;
		if (root.left != null) {
			valid = isValid(root.left, prev);
		}
		valid = valid && root.val > prev;
		prev = root.val;
		if (valid) {
			if (root.right != null) {
				return isValid(root.right, prev);
			} else {
				return true;
			}
		} else {
			return false;
		}		
	}
}
