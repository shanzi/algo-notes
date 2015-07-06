class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class SortList{
    private int length(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
    
    private ListNode merge(ListNode dummy, ListNode mid, ListNode end) {
        ListNode p, q, r;
        p = dummy.next;
        q = mid;
        r = dummy;
        while (p != mid && q != end) {
            if (p.val < q.val) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }

        if (p == mid) {
            r.next = q;
            while (r.next != end) r = r.next;
        } else {
            r.next = p;
            while (r.next != mid) r = r.next;
            r.next = end;
        }
        return r;
    }
    
    public ListNode sortList(ListNode head) {
        int len = length(head);
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode p, q, r;
        for (int i = 2; i / 2 < len; i <<= 1) {
            p = dummyHead;

            do {
                q = p;
                r = p;
                for (int j = 0; j <= i; j++) {
                    if (q != null) q = q.next;
                    if (r != null && j <= i / 2) r = r.next; 
                }
                if (r == null) break;
                else p = merge(p, r, q);
            } while (true);
        }
        return dummyHead.next;
    }
}
