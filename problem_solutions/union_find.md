# Union Find

Union find is a problem concerns about dynamic connectivity. Let's say there are $$N$$ sites
numbered from $$0$$ to $$N - 1$$. We have to type of operations, `union` connects two sites,
`find` get connected group or component one site belongs to. Two sites are connected if and only if the first site
is connected to the second site or any other sites that is connected to the second site.
All sites that belongs are connected to each other belongs to the same conneted component.

This is a quite common problem and it is equivalent to find if two nodes belongs to a same
connected component in an undirected graph. We use the root node's id to represent a
connected component.

There are several ways to do this and these methods have different time cost and space cost.

## Quick find

Quick find lets find if two sites are connected extremely fast but will cost a lot to union
two sites. In quick-find method, we change root of all of one component's sites to root of
the other. So the union operation has a time complexity of $$O(N)$$ but `find` operaion
cost $$O(1)$$ so that we can verify if two sites are belongs to one components in $$O(1)$$.

```java
public class QuickFind {
    int[] ids;

    public QuickFind(int n) { 
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public int find(int p) {
        return ids[p];
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid = qid) return;

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == pid) ids[i] = qid;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
```

## Quick union

Quick union is a converse solution to quick find, in this way, union operation may
be performed a bit faster but find operation becomes slower. To avoid there will 
be a cycle in the graph to find root, we have to check if the two sites we'd like to union
has already in the same components and return immediately doing nothing. Thus,
in the worst case, the union operation still has a time complexity of $$O(N)$$
and find operation has a time complexity of $$O(N)$$ too. Although it looks like
this algorithm is quite bad, it is the start point to futher better solutions.

```java
public class QuickUnion {
    int[] ids;

    public QuickUnion(int n) { 
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public int find(int p) {
        while (p != ids[p]) p = ids[p];
        return p;
    }

    public void union(int p, int q) {
        if (find(p) == find(q)) return;
        ids[p] = ids[q];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
```

## Weighted quick union

The main reason that quick-union algorithm may become very slow is that the connected component tree
may degenerate to be a linked list which causes `find` operation to be in linear time cost. One way
to improve is to avoid such degeneration when performing `union` operation. To achieve this,
we record the size of sites of a connected component and every time we perform `union` operation,
we always connected the root of component with lower size to that with higher size. In this case,
if a component has size $$k$$, it at most cost $$\log k$$ step to find the root. That is,
with this method, `find` operation's time cost will be $$O(\log N)$$. As we need to find a size
of a component by finding the component's root, so it will cost $$O(\log N)$$ in time for `union` operation too.

```java
public class WeightedQuickUnion {
    int[] ids;
    int[] sizes;

    public WeightedQuickUnion(int n) { 
        ids = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }
    }

    public int find(int p) {
        while (p != ids[p]) p = ids[p];
        return p;
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid == qid) return;

        if (sizes[pid] < sizes[qid]) {
            ids[pid] = ids[qid];
            sizes[qid] += sizes[pid];
        } else {
            ids[qid] = ids[pid];
            sizes[pid] += sizes[qid];
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
```

## Compressed weighted quick union

Having a worst time cost of $$O(\log N)$$, this solution is in fact quite satisfied.
But there still a way to improve it and get a very near to constant time cost for both
`union` and `find` method. The trick is during find, we immediately point parent of all sites
visited in the path to the final root. If we start doing this at the begining, the tree
generated will be very flat and will cost much less when performing `find` operation,
and thus the cost of `union` is reduced too. It has been proven that although this
algorithm is not in constant time cost, it is already the best solutions to union-find
problem.

To flatten the path, there are two way to do this. One after find the root of a component,
we perform the chasing loop again to change the parent of sites in the path to root directly.
The section way is that during chasing the root, we point the parent of a site to its grandparent.
As we start doing this at the begining and each `union` operation will has a `find` operation first,
so the depth of a tree of a connected component will grows so slow that nearly impossible to exceed 2.
It is already very near to constant.

To implement the latter method, we need only add just one line in code of weighted quick union.

```java
public class CompressedWeightedQuickUnion {
    int[] ids;
    int[] sizes;

    public CompressedWeightedQuickUnion(int n) { 
        ids = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }
    }

    public int find(int p) {
        while (p != ids[p]) {
            ids[p] = ids[ids[p]]; // The new line added
            p = ids[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid == qid) return;

        if (sizes[pid] < sizes[qid]) {
            ids[pid] = ids[qid];
            sizes[qid] += sizes[pid];
        } else {
            ids[qid] = ids[pid];
            sizes[pid] += sizes[qid];
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
```

This compress method can also be added to quick union algorithm without weighting and get a quite satisfied
time complexity without extra space cost for keep sizes. In fact, there is no reason not doing so.
It cost just one more line isn't it?
