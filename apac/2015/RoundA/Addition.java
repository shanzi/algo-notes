import java.io.*;
import java.util.*;

class UnionFind {
    private int[] ids;
    private int[] sizes;
    private int[] sums;
    private int[] types;

    public UnionFind(int N) { 
        ids = new int[N];
        sizes = new int[N];
        sums = new int[N];
        types = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }
    }

    public int find(int p) {
        if (ids[p] == p) return p;
        int root = find(ids[p]);
        if (types[p] == 0) sums[p] += sums[ids[p]];
        else sums[p] -= sums[ids[p]];
        types[p] ^= types[ids[p]];
        ids[p] = root;
        return root;
    }

    public void union(int x, int y, int sum) {
        int p = find(x);
        int q = find(y);

        if (p == q) return;

        int pqsum = sums[x] + sums[y] - sum;
        int type = types[x] ^ types[y] ^ 1;

        if (sizes[p] < sizes[q]) {
            ids[p] = q;
            sums[p] = (types[x] == 0 ? -pqsum : pqsum);
            types[p] = type;
            sizes[q] += sizes[p];
        } else {
            ids[q] = p;
            sums[q] = (types[y] == 0 ? -pqsum : pqsum);
            types[q] = type;
            sizes[p] += sizes[q];
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int pathsum(int p, int q) {
        return sums[p] + sums[q];
    }
}

public class Addition {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            String s;
            String[] str;
            UnionFind uf = new UnionFind(N * 4);
            for (int i = 0; i < N; i++) {
                s = in.next();
                str = s.split("\\+|=");
                String a = str[0];
                String b = str[1];
                int sum = Integer.parseInt(str[2]);
                if (!map.containsKey(a)) map.put(a, map.size());
                if (!map.containsKey(b)) map.put(b, map.size());
                int ia = map.get(a), ib = map.get(b);
                uf.union(ia, ib + 2 * N, sum);
                uf.union(ib, ia + 2 * N, sum);
            }
            int Q = in.nextInt();
            System.out.printf("Case #%d:\n", t + 1);
            for (int i = 0; i < Q; i++) {
                s = in.next();
                str = s.split("\\+");
                String a = str[0];
                String b = str[1];
                if (!map.containsKey(a) || !map.containsKey(b)) continue;
                int ia = map.get(a), ib = map.get(b);
                if (uf.connected(ia, ib + 2 * N)) {
                    System.out.printf("%s=%d\n", s, uf.pathsum(ia, ib + 2 * N));
                } else if (uf.connected(ia, ia + 2 * N) && uf.connected(ib, ib + 2 * N)) {
                    System.out.printf("%s=%d\n", s, (uf.pathsum(ia, ia + 2 * N) + uf.pathsum(ib, ib + 2 * N)) / 2);
                }
            }
        }
    }
}
