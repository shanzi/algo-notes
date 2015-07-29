# Reverse Nodes in k-Group

Note that if the count of elements remain is less than `k`, we should not reverse it.
So we have to check before performing reverse operation.

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseNodesInKGroup {
    private void reverse(ListNode before, ListNode after) {
        ListNode p = before.next, t;
        before.next = after;
        
        while (p != after) {
            t = p.next;
            p.next = before.next;
            before.next = p;
            p = t;
        }
    }
    
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) return head;
        
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        
        ListNode p = dummyHead, q = dummyHead.next, r;
        
        while (q != null) {
            int i;
            for (i = 0; i < k && q != null; i++) q = q.next;
            if (i < k) break;
            
            r = p.next;
            reverse(p, q);
            p = r;
        }
        
        return dummyHead.next;
    }
}
```
