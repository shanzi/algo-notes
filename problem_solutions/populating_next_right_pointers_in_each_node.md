# Populating Next Right Pointers in Each Node

Populating next right pointers in each node on a complete or perfect binary tree is simple.
Let's say the $$k$$th level has already be processed and nodes in this level have already had their next pointer
point to the correnct node. Then as for the $$k + 1$$th level, we just follow the next pointers in
$$k$$th level and thread their children one by one:

```java
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class PopulatingNextRightPointersInEachNode {
    public void connect(TreeLinkNode root) {
        TreeLinkNode p = root, q;
        while (p != null && p.left != null) {
            q = p;
            while (q != null) {
                q.left.next = q.right;
                if (q.next != null) {
                    q.right.next = q.next.left;
                } else {
                    q.right.next = null;
                }
                q = q.next;
            }
            p = p.left;
        }
        
    }
}
```

If the tree is not a complete or perfect binary tree, we have to take care of a lot more:

1. We need to preserve the first non-null node in a level instead of just advanced to left child
of the left most node.
2. We need handle a pointer in $$k + 1$$ level to thread last non-null node with the next non-null node
instead of just thread children.

```java
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class PopulatingNextRightPointersInEachNodeII {
    public void connect(TreeLinkNode root) {
        TreeLinkNode p, q, r, s;
        p = root;
        
        while (p != null) {
            
            q = p;
            r = null;
            s = null;
            
            while (q != null) {
                if (r != null) {
                    if (q.left != null) {
                        r.next = q.left;
                    } else if (q.right != null) {
                        r.next = q.right;
                    }
                }
                
                if (q.left != null && q.right != null) {
                    q.left.next = q.right;
                    r = q.right;
                } else if (q.left != null) {
                    r = q.left;
                } else if (q.right != null) {
                    r = q.right;
                }
                
                if (s == null) {
                    if (q.left != null) s = q.left;
                    else if (q.right != null) s = q.right;
                }
                
                q = q.next;
            }
            
            
            p = s;
        }
    }
}
```
