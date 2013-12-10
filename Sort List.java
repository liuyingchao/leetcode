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
 // MY NOTE: this code is very ugly. Revisit how to refactor the loop body.
public class Solution {
	public ListNode sortList(ListNode head) {
		int length = getLength(head);
		if (length <= 1) {
			return head;
		}
		
		// Split
        int mergeLength = length/ 2;
        ListNode tail = getFirstHalfTail(head, mergeLength);
        // Set up 2 lists to make sure they are valid lists for merge
        ListNode secondHalf = tail.next;
        tail.next = null;
        
        // Sort the first half
        head = sortList(head);
        // Sort the second half
        secondHalf = sortList(secondHalf);
        // merge
        return mergeTwoLists(head, secondHalf);
    }
	
	private int getLength(ListNode head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}
	
	private ListNode getFirstHalfTail(ListNode head, int firstHalfLength) {
		int count = firstHalfLength - 1;
		while (count > 0) {
			head = head.next;
			count--;
		}
		return head;
	}
	
	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    ListNode dummy = new ListNode(0);
	    ListNode current = dummy;
	    ListNode min;
	    boolean l1Selected;
	    while (l1 != null && l2 != null) {
            l1Selected = l1.val < l2.val;
            min = l1Selected ? l1 : l2;
            current.next = min;
            current = current.next;
            if (l1Selected) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
	    }
	    if (l1 == null) {
            current.next = l2;
	    } 
	    if (l2 == null) {
            current.next = l1;
	    }
	            
        return dummy.next;
	}
}

