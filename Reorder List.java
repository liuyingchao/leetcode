import java.util.*;
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
    	if (head != null) {
    		Stack topStack = new Stack();
            int total = 0;
            while (head != null) {
            	topStack.push(head);
            	head = head.next;
            	total++;
            }
            int half = total/2;
            Stack bottomStack = new Stack();
            for (int i = half; i > 0; i--) {
            	bottomStack.push(topStack.pop());
            }
            
            // Differentiate between odd and even number of nodes
            if (total % 2 == 0) {
            	head = null;
            } else {
            	head = (ListNode) topStack.pop();
            	head.next = null;
            }
            
            while (!topStack.empty()) {
            	ListNode top = (ListNode) topStack.pop();
            	ListNode bottom = (ListNode) bottomStack.pop();
            	bottom.next = head;
            	top.next = bottom;
            	head = top;
            }
    	}
    }
}
