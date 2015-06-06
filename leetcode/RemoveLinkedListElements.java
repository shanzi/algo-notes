public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode pHead = new ListNode(0);
        pHead.next = head;
        ListNode p = pHead;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else p = p.next; // shift p to p.next only when p.next.val != val
        }
        return pHead.next;
    }
}
