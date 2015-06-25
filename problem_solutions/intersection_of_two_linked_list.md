# Intersection of Two Linked List

At first we get the length of two list. Then we use two pointers that respectively pointing to heads
to two linked list. Then we move the pointer on the longer list until the distance of the pointer
to the tail is the same to that of the shorter list. At last we move the two pointer at the same
time many times until they are pointing to the same node(or null). Then that node is the beginning
of intersection.

```java
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
public class IntersectionOfTwoLinkedList {
    private int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        
        ListNode longer, shorter;
        int delta = 0;
        if (lenA >= lenB) {
            longer = headA;
            shorter = headB;
            delta = lenA - lenB;
        } else {
            longer = headB;
            shorter = headA;
            delta = lenB - lenA;
        }
        
        while (delta > 0) {
            longer = longer.next;
            delta--;
        }
        
        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        
        return longer;
    }
}
```
