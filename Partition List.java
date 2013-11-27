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
    public ListNode partition(ListNode head, int x) {
        ListNode backDummy = new ListNode(0);
        backDummy.next = head;
        ListNode frontDummy = new ListNode(0);
        
        ListNode prev = backDummy;
        ListNode frontCurrent = frontDummy;
        ListNode current = prev.next;
        
        while (current != null) {
        	if (current.val >= x) {
        		prev = current;
        		current = current.next;
        	} else {
        		frontCurrent.next = current;
        		frontCurrent = current;
        		// Notice that we may leave frontCurrent.next dangling to wrong place,
        		// but it's a good invariant to set it to null. Therefore, we don't
        		// extract current=current.next outside this if-else
        		current = current.next;
        		frontCurrent.next = null;
        	}
        	// Important to keep this invariant: otherwise for input "{1} 2", we'll return a list with cycle
        	prev.next = current;
        }
        
        if (frontCurrent != frontDummy) {
        	frontCurrent.next = backDummy.next;
        	backDummy.next = frontDummy.next;
        } // else there is nothing in frontDummy, i.e. we just traverse the list without moving any node==>simply return the original list
        
        return backDummy.next;
    }
}
