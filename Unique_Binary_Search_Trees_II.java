/**
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
 Difficulty: Hard
 Solution: DP with ArrayList as cache entries. Notice we need dimension of n+2 by n+2 to cover the leftmost and rightmost children.
 And the start>end logic is beautifully combined with boundary code to avoid any initialization.
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
        
        return getTrees(1, n);
    }
    
    private ArrayList<TreeNode> getTrees(int start, int end) {
    	ArrayList<TreeNode> al = new ArrayList<TreeNode>();
    	if (start > end) {  // Unnecessary to initialize the diagonal nodes. We can rely on the code to set their left and right to null
    		al.add(null);
    	} else {
    		if (table[start][end] != null) {
        		return table[start][end];
        	}
    		for (int i = start; i <= end; i++) {
            	ArrayList<TreeNode> leftTrees = getTrees(start, i - 1);
            	ArrayList<TreeNode> rightTrees = getTrees(i + 1, end);
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
