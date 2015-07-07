# Reorder List

We can use a stack to solve this problem, either to save the first half of nodes
or the last half of nodes. Things that should be pay attention to:

1. We use faster runner and slow runner to find the middle node.
2. Based on the faster runner's last position, we can decide if length of the list is odd
or even and thus perform different tweak on the middle node.
3. Remember to handle the empty list case.


Let the stack preserve the latter half of list nodes, we get the answer like:
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) return;
        
        ListNode p, t;
        Stack<ListNode> stack = new Stack<ListNode>();
        p = head;
        t = head;
        
        while (p != null && p.next != null) {
            t = t.next;
            p = p.next.next;
        }
        
        p = t.next;
        t.next = null;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        
        p = head;
        while (!stack.isEmpty()) {
            t = stack.pop();
            t.next = p.next;
            p.next = t;
            p = t.next;
        }
    }
}
```

Let the stack preserve the first half of list nodes, we get the answer like:
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) return;
        
        ListNode p, t;
        Stack<ListNode> stack = new Stack<ListNode>();
        p = head;
        t = head;
        
        while (p != null && p.next != null) {
            stack.push(t);
            t = t.next;
            p = p.next.next;
        }
        
        if (p != null) {
            stack.push(t);
        }
        
        p = t.next;
        t.next = null;
        
        ListNode last, top;
        
        while (p != null) {
            last = stack.pop();
            top = stack.peek();
            top.next = p;
            p = p.next;
            top.next.next = last;
        }
    }
}
```
