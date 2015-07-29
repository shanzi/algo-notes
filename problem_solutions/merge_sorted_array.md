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
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    private ListNode mergeKLists(ListNode[] lists, int a, int b) {
        if (a == b) return lists[a];
        
        int mid = (a + b) / 2;
        ListNode left = mergeKLists(lists, a, mid);
        ListNode right = mergeKLists(lists, mid + 1, b);
        
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        
        while (left != null && right != null) {
            if (left.val <= right.val) {
                p.next = left;
                left = left.next;
            } else {
                p.next = right;
                right = right.next;
            }
            
            p = p.next;
        }
        
        if (left == null) {
            p.next = right;
        } else {
            p.next = left;
        }
        
        return dummyHead.next;
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        
        return mergeKLists(lists, 0, lists.length - 1);
    }
}
```

For merge sorted array, there are another solution of time cost $$O(N\log K)$$ but is more space efficiently
when `K` is small. That is using a `Heap` or `PriorityQueue`.  At first we pick the first item from each array
and put it into a `PriorityQueue`. When doing this, we also record which array this element is from.
Everytime we pick an item from the `PriorityQueue` and insert it to the merged array.
Then, we examine which array this item is from and pick a new item from that array to `PriorityQueue`.
The space cost will be $$O(N + K)$$ for this solution as the `PriorityQueue` needs `K` units more spaces.
