/*
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

 * */
import java.util.*;
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
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    	if (root != null) {
    		boolean isForward = false;
    		ArrayList<TreeNode> currentNodes = new ArrayList<TreeNode>();
    		currentNodes.add(root); 
    		ArrayList<Integer> currentInts = new ArrayList<Integer>();
    		currentInts.add(root.val);
    		res.add(currentInts);
    		while (!currentNodes.isEmpty()) {
    			ArrayList<TreeNode> childrenNodes = new ArrayList<TreeNode>();
    			currentInts = new ArrayList<Integer>();
    			int total = currentNodes.size();
    			// We always traverse currentNodes "backward", because we scan each level in the level different from the parent level.
    			// That means, we always start with the last node of the parent level.
    			for (int i = total - 1; i >= 0; i--) {
    				TreeNode node = currentNodes.get(i);
    				addChildren(node, childrenNodes, currentInts, isForward);
    			}
    			if (!currentInts.isEmpty()) {
    				res.add(currentInts);
    			}
    			isForward = !isForward;
    			currentNodes = childrenNodes;
    		}
    	}
    	return res;
    }
	
	private void addChildren(TreeNode root, ArrayList<TreeNode> childrenNodes, ArrayList<Integer> ints, boolean isForward) {
		TreeNode first = root.left;
		TreeNode second = root.right;
		if (!isForward) {
			first = root.right;
			second = root.left;
		}
		if (first != null) {
			childrenNodes.add(first);
			ints.add(first.val);
		}
		if (second != null) {
			childrenNodes.add(second);
			ints.add(second.val);
		}
	}
}
