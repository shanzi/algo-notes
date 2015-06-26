# Merge Sorted Array/List

Merge two sorted array is simple:

```java
public class MergeTwoSortedArray {
    public void merge(int A[], int m, int B[], int n) {
        while (m > 0 && n > 0) {
            int idx = m + n - 1;
            int va = A[m - 1];
            int vb = B[n - 1];
            if (va > vb) {
                A[idx] = va;
                m--;
            } else {
                A[idx] = vb;
                n--;
            }
        }
        
        while (n > 0) {
            A[n - 1] = B[n - 1];
            n--;
        }
        
    }
}
```

The code below shows how to merge two linked list:

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
public class MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        
        ListNode p = dummyHead;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                p = p.next;
                l1 = l1.next;
            } else {
                p.next = l2;
                p = p.next;
                l2 = l2.next;
            }
        }
        
        if (l1 != null) p.next = l1;
        else p.next = l2;
        
        return dummyHead.next;
    }
}
```

But merge `K` sorted array is a little complex. If we are going to pick the smallest number from
`K` list, it will cost time $$O(K)$$, we have to pick $$O(N)$$ time. So the total time cost is $$O(N\times K)$$.
If `K` is very large, it will not be efficient enougth.

One alternative way is to use divide and conquer method and merge the list recursively. In this way
we get a time complexity of $$O(N\log K)$$. Its space cost $$O(2N)$$ for array sort is a bit huge,
but this method is quite a simple and fast solution and it is very space efficient for Linked list.

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
public class MergeKSortedList {
    public ListNode mergeKLists(List<ListNode> lists, int start, int end) {
        if (start == end) return lists.get(start);
        
        int mid = (start + end) / 2;
        ListNode leftHead = mergeKLists(lists, start, mid);
        ListNode rightHead = mergeKLists(lists, mid + 1, end);
        
        ListNode dummyHead = new ListNode(0);
        
        ListNode p = dummyHead;
        
        while (leftHead != null && rightHead != null) {
            if (leftHead.val <= rightHead.val) {
                p.next = leftHead;
                p = p.next;
                leftHead = leftHead.next;
            } else {
                p.next = rightHead;
                p = p.next;
                rightHead = rightHead.next;
            }
        }
        
        if (leftHead != null) {
            p.next = leftHead;
        } else {
            p.next = rightHead;
        }
        
        return dummyHead.next;
    }
    
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists.isEmpty()) return null;
        else return mergeKLists(lists, 0, lists.size() - 1);
    }
}
```

For merge sorted array, there are another solution of time cost $$O(N\log K)$$ but is more space efficiently
when `K` is small. That is using a `Heap` or `PriorityQueue`.  At first we pick the first item from each array
and put it into a `PriorityQueue`. When doing this, we also record which array this element is from.
Everytime we pick an item from the `PriorityQueue` and insert it to the merged array.
Then, we examine which array this item is from and pick a new item from that array to `PriorityQueue`.
The space cost will be $$O(N + K)$$ for this solution as the `PriorityQueue` needs `K` units more spaces.
