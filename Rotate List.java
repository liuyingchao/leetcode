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
public class Solution {
    public ListNode rotateRight(ListNode head, int n) {
    	if (head == null || head.next == null) {
    		return head;
    	} 
    	int length = 0;
    	ListNode current = head;
    	ListNode tail = null;
    	while (current != null) {
    		length++;
    		tail = current;
    		current = current.next;
    	}
    	
    	// Be careful that the value we set here determines it's a right shift or a left shift
    	int shift = length - n % length;
    	if (shift == length) {
    		return head;
    	}
    	
    	current = head;
    	for (int i = 1; i < shift; i++) {
    		current = current.next;
    	}
    	
    	ListNode newHead = current.next;
    	
    	// Hook up
    	tail.next = head;
    	current.next = null;
    	head = newHead;
    	return head;
    }
}
