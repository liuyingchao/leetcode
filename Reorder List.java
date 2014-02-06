/* My NOTES: for linkedList functions, after breaking big chunk of code into smaller functions, be aware that
 * we need to use return value to indicate what's the desired output list's head. The input parm is passed by value,
 * which means the old head still points to some old value in the middle of the list. That's the root cause of my initial bugs.
 * */

import java.util.*;
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
    public void reorderList(ListNode head) {
    	ListNode backup = head;
    	if (head != null && head.next != null) {
    		ListNode tail = findTail(head);
    		// Break into 2 halves
            ListNode secondHead = tail.next;
            // Then reverse
            secondHead = reverseList(secondHead);
            tail.next = null;

            // Merge
            head = backup;
            head = mergeLists(head, secondHead);
            return;
    	}
    }
    
    private ListNode findTail(ListNode head) {
    	ListNode backup = head;
    	int total = 0;
        while (head != null) {
        	head = head.next;
        	total++;
        }
        // find tail
        int half = (total+1)/2;
        head = backup;
        for (int i = 0; i < half - 1; i++) {
        	head = head.next;
        }            
        return head;
    }
    
    private ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        while (head != null) {
        	ListNode temp = head.next;
        	head.next = dummy.next;
        	dummy.next = head;
        	head = temp;
        }
    	
        return dummy.next;
    }
    
    private ListNode mergeLists(ListNode firstHead, ListNode secondHead) {
    	ListNode backup = firstHead;
    	ListNode node = secondHead;
        ListNode firstNext, secondNext;
        // Make sure we use the second list as the loop invariant, in case the first list is longer by one node.
        while (node != null) {	
        	firstNext = firstHead.next;
        	secondNext = node.next;
        	node.next= firstNext;
        	firstHead.next = node;
        	firstHead = firstNext;
        	node = secondNext;
        }
        return backup;
    }
}



import java.util.*;
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
 
// O(1) space solution
public class Solution {
    public void reorderList(ListNode head) {
    	ListNode backup = head;
    	if (head != null && head.next != null) {
    		// count
    		int total = 0;
            while (head != null) {
            	head = head.next;
            	total++;
            }
            // find tail
            int half = (total+1)/2;
            head = backup;
            for (int i = 0; i < half - 1; i++) {
            	head = head.next;
            }            
            ListNode tail = head;
            
            // Break the list into half
            ListNode secondHead = new ListNode(0);
            secondHead.next = tail.next;
            tail.next = null;
            
            // Reverse the second half
            ListNode secondTail = secondHead.next;
            ListNode node = secondTail;
            ListNode nextNode = node.next;
            int secondHalf = total - half;
            // There are at least 2 nodes in the original list, so secondHalf is at least 1
            // If secondHalf is 1, we are already good
            for (int i = 0; i < secondHalf - 1; i++) {
            	node = nextNode;
            	nextNode = node.next;
            	node.next = secondHead.next;
            	secondHead.next = node;
            }
            secondTail.next = null;
            
            // Merge
            head = backup;
            node = secondHead.next;
            ListNode topNext, secondNext;
            // Make sure we use the second list as the loop invariant, in case the first list is longer by one node.
            while (node != null) {	
            	topNext = head.next;
            	secondNext = node.next;
            	node.next= topNext;
            	head.next = node;
            	head = topNext;
            	node = secondNext;
            }
            
            head = backup;
            return;
    	}
    }
}


 // MY NOTE: this code actually uses additional O(n) space. The real "in-place"
 // solution is running the same logic, just starting with fast/slow pointers,
 // and then do list reverse on the second half, and then merge the 2. Just
 // slightly more tricky.
public class Solution {
    public void reorderList(ListNode head) {
    	if (head != null) {
    		Stack topStack = new Stack();
            int total = 0;
            while (head != null) {
            	topStack.push(head);
            	head = head.next;
            	total++;
            }
            int half = total/2;
            Stack bottomStack = new Stack();
            for (int i = half; i > 0; i--) {
            	bottomStack.push(topStack.pop());
            }
            
            // Differentiate between odd and even number of nodes
            if (total % 2 == 0) {
            	head = null;
            } else {
            	head = (ListNode) topStack.pop();
            	head.next = null;
            }
            
            while (!topStack.empty()) {
            	ListNode top = (ListNode) topStack.pop();
            	ListNode bottom = (ListNode) bottomStack.pop();
            	bottom.next = head;
            	top.next = bottom;
            	head = top;
            }
    	}
    }
}
