/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
import java.util.Queue;
import java.util.LinkedList;
 
// MY note: the requirements about tree being a perfect binary tree is unnecessary.
public class Solution {
    public void connect(TreeLinkNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        TreeLinkNode dummy = new TreeLinkNode(0);
        if (root != null) {
            queue.add(root);
            queue.add(null);
            // Set prev to a dummy node.
            TreeLinkNode prev = dummy;
            TreeLinkNode current = null;
            while(!queue.isEmpty()) {
                current = queue.poll();
                prev.next = current;
                if (current == null) {
                    if (queue.peek() != null) {
                        queue.add(null);
                        prev = dummy;
                    }
                } else {
                    if (current.left != null) {
                        queue.add(current.left);
                    }
                    if (current.right != null) {
                        queue.add(current.right);
                    }
                    prev =  current;
                }
            }
        }
    }
}
