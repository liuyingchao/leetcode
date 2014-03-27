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
 
// My NOTES: there is code duplicate in those 2 while loops, but it's not easy to refactor.
// I'll revisit.
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
        	return null;
        }
        int sum, value;
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (l1 != null && l2 != null) {
        	sum = l1.val + l2.val + carry;
        	carry = sum / 10;
        	value = sum % 10;
        	current.next = new ListNode(value);
        	current = current.next;
        	l1 = l1.next;
        	l2 = l2.next;
        }
        
        ListNode remain = l1 == null ? l2 : l1;
        while (remain != null) {
        	sum = remain.val + carry;
        	carry = sum / 10;
        	value = sum % 10;
        	current.next = new ListNode(value);
        	current = current.next;
        	remain = remain.next;
        }
        
        if (carry == 1) {
        	current.next = new ListNode(1);
        }
        
        return dummy.next;
    }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
        	return null;
        }
        int sum, value;
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode current;
        while (l1 != null && l2 != null) {
        	sum = l1.val + l2.val + carry;
        	carry = sum / 10;
        	value = sum % 10;
        	current =  new ListNode(value);
        	prev.next = current;
        	prev = current;
        	l1 = l1.next;
        	l2 = l2.next;
        }
        
        ListNode remain = l1 == null ? l2 : l1;
        while (remain != null) {
        	sum = remain.val + carry;
        	carry = sum / 10;
        	value = sum % 10;
        	current = new ListNode(value);
        	prev.next = current;
        	prev = current;
        	remain = remain.next;
        }
        
        if (carry == 1) {
        	current = new ListNode(1);
        	prev.next = current;
        }
        
        return dummy.next;
    }
}
