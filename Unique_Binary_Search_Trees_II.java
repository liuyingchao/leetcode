import java.util.ArrayList;


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution {
    private ArrayList<TreeNode>[][] table;
    private int N;
    public ArrayList<TreeNode> generateTrees(int n) {
        if (n <= 0) {
        	ArrayList<TreeNode> result = new ArrayList();
        	result.add(null);
        	return result;
        }
        N = n;
        table = new ArrayList[n+2][n+2];
        ArrayList<TreeNode> al = new ArrayList<TreeNode>();
        al.add(null);
        table[0][0] = al;
        
        for (int i = 1; i <= n; i++) {
        	al = new ArrayList<TreeNode>();
        	TreeNode node = new TreeNode(i);
        	al.add(node);
        	table[i][i] = al;
        }
        
        return getTrees(1, n, 0, n+1);
    }
    
    private ArrayList<TreeNode> getTrees(int start, int end, int lowerBound, int upperBound) {
    	ArrayList<TreeNode> al = new ArrayList<TreeNode>();
    	if (start > end || start <= lowerBound || end >= upperBound) {
    		al.add(null);
    	} else {
    		if (table[start][end] != null) {
        		return table[start][end]; 
        	}
    		for (int i = start; i <= end; i++) {
            	ArrayList<TreeNode> leftTrees = getTrees(start, i - 1, lowerBound, i);
            	ArrayList<TreeNode> rightTrees = getTrees(i + 1, end, i, upperBound);
            	for (TreeNode leftNode : leftTrees) {
            		for (TreeNode rightNode: rightTrees) {
            			TreeNode root = new TreeNode(i);
            			root.left = leftNode;
            			root.right = rightNode;
            			al.add(root);
            		}
            	}
            }
    		// My comment: for some reason, when I forgot to cache this result, the code is still accepted. 
    		// The root cause seems to be: even though this is DP, it's very special in the sense that the cache
    		// isn't really used at all. The result is built bottom-up, which means essentially a smaller amount
    		// of memory usage would be good enough.
    		table[start][end] = al;
     	}
    	
        return al;
    }
}
