/**
 Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Difficulty: Medium 
Solution: The biggest problem to solve is when we exam a node, we need to decide whether we should go to handle
the left child or add it into output and move on to the right child. This implies we can process this info with
a boolean--that's the "leftProcessed" flag. So the idea is to push a node to stack with flag being false; before we
process the left, we turn the flag to true and push the root node back into stack. The next time we pop root out of
the stack, we know it's safe to add it into the result list, and move on to handle the right child.
 */
public class Solution {
    class StackNode {
        public TreeNode treeNode;
        public boolean leftProcessed;
        
        public StackNode(TreeNode treeNode, boolean leftProcessed) {
            this.treeNode = treeNode;
            this.leftProcessed = this.leftProcessed;
        }
    }
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        
        Stack s = new Stack();
        s.push(new StackNode(root, false));
        
        while (!s.isEmpty()) {
            StackNode stackNode = (StackNode) s.pop();
            TreeNode treeNode = stackNode.treeNode;
            if (stackNode.leftProcessed || treeNode.left == null) {
                // Output and move on to the right child
                res.add(treeNode.val);
                if (treeNode.right != null) {
                    s.push(new StackNode(treeNode.right, false));
                }
            } else {
                stackNode.leftProcessed = true;
                // Need to push the node back to the stack, because we have to take it out before updating its value
                s.push(stackNode);
                s.push(new StackNode(treeNode.left, false));
            }
        }
        
        return res;
    }
}
