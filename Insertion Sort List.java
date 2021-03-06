/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
// This version is an improvement that gets rid of "end" 
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        // head.next == null can be handled by the code after the first if branch,
        // but it's not clear whether removing this OR operator will be better code
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode dummy = new ListNode(Integer.MIN_VALUE);
    	dummy.next = head;
    	ListNode start = dummy;
    	ListNode current = head.next;
    	head.next = null;
    	while (current != null) {
    		ListNode incoming = current.next;
    		ListNode prev = start;
    		ListNode loopNode = start.next;
    		while (loopNode != null && loopNode.val < current.val) {
    			prev = loopNode;
    			loopNode = loopNode.next;
    		}
    		current.next = loopNode;
    		prev.next = current;
    		
    		current = incoming;
    	}
    	return dummy.next;
    }
} 
 
public class Solution {
    public ListNode insertionSortList(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode dummy = new ListNode(Integer.MIN_VALUE);
    	dummy.next = head;
    	ListNode start = dummy;
    	ListNode end = head;
    	ListNode current = head.next;
    	// end seems to be redundant. It may be replaced by prev
    	end.next = null;
    	while (current != null) {
    		ListNode incoming = current.next;
    		ListNode prev = start;
    		ListNode loopNode = start.next;
    		while (loopNode != null && loopNode.val < current.val) {
    			prev = loopNode;
    			loopNode = loopNode.next;
    		}
    		
    		if (loopNode == null) {
    			end.next = current;
    			current.next = null;
    			end = current;
    		} else {
    			current.next = loopNode;
    			prev.next = current;
    		}
    		current = incoming;
    	}
    	return dummy.next;
    }
}
