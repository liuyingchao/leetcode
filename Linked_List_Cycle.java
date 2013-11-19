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

        ListNode fast = head;
        ListNode slow = head;
        
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

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
        	return false;
        }
        ListNode fast = head.next.next;
        ListNode slow = head;
        
        while (true) {
        	if (fast == slow) {
        		return true;
        	}
        	
        	if (fast == null || fast.next == null) {
        		return false;
        	}
        	
        	slow = slow.next;
        	fast = fast.next.next;
        }
    }
}
