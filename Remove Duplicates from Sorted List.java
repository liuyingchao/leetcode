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
        if (head != null) {
        	ListNode prev = head;
        	ListNode current = head.next;
        	while (current != null) {
        		if (current.val == prev.val) {
        			prev.next = current.next;
        		} else {
        			prev = current;
        		}        		
        		current = current.next;
        	}
        }
        return head;
    }
}
