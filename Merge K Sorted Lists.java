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
import java.util.*;
public class Solution {
	class MyComparator implements Comparator<ListNode> {
		public int compare(ListNode a, ListNode b) {
			if (a.val < b.val) {
				return -1;
			} else if (a.val > b.val) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
    	int K = lists.size();
    	if (K == 0) return null;
    	
    	Comparator<ListNode> comparator =  new MyComparator();
    	PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(K, comparator);
    	ListNode dummy = new ListNode(0);
    	for (int i = 0; i < K; i++) {
    		if (lists.get(i) != null) {
        		heap.add(lists.get(i));	
    		}
    	}
    	
    	ListNode current = dummy;
    	
    	while (!heap.isEmpty()) {
    		ListNode top = heap.poll();
    		current.next = top;
    		current = current.next;
    		if (top.next != null) {
    			heap.add(top.next);
    		}
    	}
    	return dummy.next;
    }
}
