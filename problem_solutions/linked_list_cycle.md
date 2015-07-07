# Linked List Cycle

To decide if a singly linked list has a cycle and find the start node of cycle is such
and classical problem. Computer scientist spent some years before find this standard solution.
Like many other problems concerns about linked list, in this problem we also need to pointer:
a `fastRunner` and a `slowRunner`. The `fastRunner` moves two steps every time while `slowRunner` moves one.
At last either the two pointers are pointing the same node, or the `fastRunner` meets the end of
the list. In the former case, there must be a cycle in the list. In the latter case, there are no cycle.

To find the start node, after the two pointers meets, we move `slowRunner` back to the `head` and moves
two pointer one step every time in parallel until they meet again. The node they meet at is the start point
of cycle.

Note at first both pointers point to `head`, at this time we cannot say they meet and we must wait
for the second time they are both pointing to a same node.

```java
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
public class LinkedListCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode fastRunner = head, slowRunner = head;
        
        while (fastRunner != null && fastRunner.next != null) {
            fastRunner = fastRunner.next.next;
            slowRunner = slowRunner.next;
            
            if (fastRunner == slowRunner) {
                // find a cycle
                slowRunner = head;
                while (slowRunner != fastRunner) {
                    slowRunner = slowRunner.next;
                    fastRunner = fastRunner.next;
                }
                
                return slowRunner;
            }
        }
        
        return null;
    }
}
```
