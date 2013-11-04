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
    public ListNode swapPairs(ListNode head) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        if (head != null && head.next != null) {
            ListNode runnerUp = head.next;
            head.next = runnerUp.next;
            runnerUp.next = head;
            dummy.next = runnerUp;
            head.next = swapPairs(head.next);
        }
        return dummy.next;
    }
}
