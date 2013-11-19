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
    public boolean hasCycle(ListNode head) {
        if (head == null) {
        	return false;
        }

        ListNode dummy =  new ListNode(0);
        dummy.next =  head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        while (fast != null && fast.next != null) {
        	fast = fast.next;
        	
        	if (fast == slow) {
        		return true;
        	}
        	
        	slow = slow.next;
        	
        	if (fast.next == null) {
        		return false;        		
        	} else {
        		fast = fast.next;
        		if (fast == slow) {
            		return true;
            	}
        	}
        }
        
        return false;
    }
}
