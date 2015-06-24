# Reverse Linked List

Reverse a singly linked list is a simple problem. Iterative and recursive solutions are given below.
Note in the recursively solutions we persist a pointer to `head.next` before calling the function
on next node as after reverse the list with `head.next` as head, `head.next` will become tail
in the partial list. So we can always return new head for the list without losing the pointer
to the tail.

Iterasive solution:
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        
        ListNode preHead = new ListNode(0); // we need not a stack to save nodes
        while (head != null) {
            ListNode next = head.next;
            head.next = preHead.next;
            preHead.next = head;
            head = next;
        }
        return preHead.next;
    }
}
```

Recursive solution:
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        if (head.next == null) return head;
        
        ListNode next = head.next;
        ListNode newHead = reverseList(next);
        head.next = null;
        next.next = head;
        return newHead;
    }
}
```
