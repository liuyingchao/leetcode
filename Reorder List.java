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
 
// O(1) space solution
public class Solution {
    public void reorderList(ListNode head) {
    	ListNode backup = head;
    	if (head != null && head.next != null) {
    		// count
    		int total = 0;
            while (head != null) {
            	head = head.next;
            	total++;
            }
            // find tail
            int half = (total+1)/2;
            head = backup;
            for (int i = 0; i < half - 1; i++) {
            	head = head.next;
            }            
            ListNode tail = head;
            
            // Break the list into half
            ListNode secondHead = new ListNode(0);
            secondHead.next = tail.next;
            tail.next = null;
            
            // Reverse the second half
            ListNode secondTail = secondHead.next;
            ListNode node = secondTail;
            ListNode nextNode = node.next;
            int secondHalf = total - half;
            // There are at least 2 nodes in the original list, so secondHalf is at least 1
            // If secondHalf is 1, we are already good
            for (int i = 0; i < secondHalf - 1; i++) {
            	node = nextNode;
            	nextNode = node.next;
            	node.next = secondHead.next;
            	secondHead.next = node;
            }
            secondTail.next = null;
            
            // Merge
            head = backup;
            node = secondHead.next;
            ListNode topNext, secondNext;
            // Make sure we use the second list as the loop invariant, in case the first list is longer by one node.
            while (node != null) {	
            	topNext = head.next;
            	secondNext = node.next;
            	node.next= topNext;
            	head.next = node;
            	head = topNext;
            	node = secondNext;
            }
            
            head = backup;
            return;
    	}
    }
}


 // MY NOTE: this code actually uses additional O(n) space. The real "in-place"
 // solution is running the same logic, just starting with fast/slow pointers,
 // and then do list reverse on the second half, and then merge the 2. Just
 // slightly more tricky.
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
