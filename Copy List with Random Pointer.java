/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Difficulty: Hard

Solution: Hash!
Looking into the solution, HashMap essentially provides an additional pointer that we cannot add into the original objects. This can be
a useful trick to deal with many problems facing the constraint of not changing the original data.

It's easy to create the list with next pointers in place. The trick is: during the creation process, we create a hashMap from the original
node to the copied node. This way we can guarantee during our second scan, we can trust the random pointer and consume its hash value 
to get exactly the node we are looking for, and we don't get bothered by backward pointers.

 * 
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        
        RandomListNode dummy = new RandomListNode(0);
        dummy.next = head;
        RandomListNode copyDummy = new RandomListNode(0);
        
        RandomListNode current = dummy;
        RandomListNode next;
        RandomListNode copyCurrent = copyDummy;
        RandomListNode copyNext;
        HashMap<RandomListNode, RandomListNode> hash = new HashMap<RandomListNode, RandomListNode>();
        while (current != null) {
            next = current.next;
            if (next == null) {
                copyNext = null;
            } else {
                copyNext = new RandomListNode(next.label);
            }
            copyCurrent.next = copyNext;
            // Create the hash entry
            hash.put(current, copyCurrent);
            current = next;
            copyCurrent = copyNext;
        }
    
        current = dummy;
        copyCurrent = copyDummy;
        RandomListNode random;
        while (current != null) {
            random = current.random;
            // read HashMap value and set the random pointer.
            if (random != null) {   // Be careful not to hit null random pointers.
                copyCurrent.random = hash.get(random);
            }
            current = current.next;
            copyCurrent = copyCurrent.next;
        }
        
        return copyDummy.next;
    }
}
