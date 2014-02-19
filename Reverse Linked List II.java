/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

Solution: find the node before m, and count how many we reversed, then point
first.next to the right point. Be cautious with the boundary condition of those 2 loops.

 * 
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode current = dummy;
        int count = 1;
        while (count < m) {  // Be cautious with off by 1 bug.
            current = current.next;
            count++;
        }
        
        head = current;
        ListNode first = head.next;
        head.next = null;
        current = first;
        ListNode temp;
        // count == m at this moment
        while (count <= n) {    // Be careful with this boundary check.
            temp = current.next;
            current.next = head.next;
            head.next = current;
            current = temp;
            count++;
        }
        first.next = current;
        return dummy.next;
    }
}
