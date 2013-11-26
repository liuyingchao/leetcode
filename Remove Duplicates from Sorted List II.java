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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        boolean duplicate = false;
        ListNode prev = dummy;
        ListNode current = head;
        while (current != null) {
        	while (current.next != null && current.next.val == current.val) {
        		duplicate = true;
        		current.next = current.next.next;        		
        	}
        	if (duplicate) {
        		prev.next = current.next;
        		duplicate = false;
        	} else {
        		prev = current;
        	}
        	current = prev.next;
        }
        
        return dummy.next;
    }
}
