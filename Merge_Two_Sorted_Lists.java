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
// A much cleaner solution
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ListNode dummy = new ListNode(0);
        if (l1 == null) {
            dummy.next = l2;
        } else if (l2 == null)  {
            dummy.next = l1;
        } else { // If both are null, dummy.next is already correctly null, so we can proceed in the first branch
                 // without reaching this branch of both having values.
			  boolean moveFirst = l1.val <= l2.val;			
            dummy.next = moveFirst ? l1 : l2;            
    		ListNode prev = dummy;
			
        while (l1 != null && l2 != null) {
				if (moveFirst) {
					if (l1.val <= l2.val) {
						prev = l1;
						l1 = l1.next;
					} else {
						moveFirst = false;
						prev.next = l2;
						prev = l2;
						l2 = l2.next;
					}					
				} else {
					if (l2.val < l1.val) {
						prev = l2;
						l2 = l2.next;
					} else {
						moveFirst = true;
						prev.next = l1;
						prev = l1;
						l1 = l1.next;
					}					
				}
            }
			
			if (moveFirst) {
				prev.next = l2;
			} else {
				prev.next = l1;
			}
        }
        
		return dummy.next;
    }
}
