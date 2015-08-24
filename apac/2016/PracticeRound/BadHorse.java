import java.io.*;
import java.util.*;

public class BadHorse {
    static int[] ids;
    static int[] sizes;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int T = in.nextInt();
            
            for (int t = 0; t < T; t++) {
                int m = in.nextInt();
                int number = 0;
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                ids = new int[4 * m];
                sizes = new int[4 * m];
                int N = 2 * m;

                for (int i = 0; i < ids.length; i++) {
                    ids[i] = i;
                    sizes[i] = 1;
                }

                boolean flag = true;

                for (int i = 0; i < m; i++) {
                    String a = in.next();
                    String b = in.next();

                    int aid = 0;
                    int bid = 0;

                    if (map.containsKey(a)) {
                        aid = map.get(a);
                    } else {
                        aid = number++;
                        map.put(a, aid);
                    }

                    if (map.containsKey(b)) {
                        bid = map.get(b);
                    } else {
                        bid = number++;
                        map.put(b, bid);
                    }

                    if (connected(aid, bid)) {
                        flag = false;
                    } else {
                        union(aid, bid + N);
                        union(bid, aid + N);
                    }
                }

                System.out.printf("Case #%d: %s\n", t + 1, (flag ? "Yes" : "No"));
            }
        }
    }

    static private int find(int p) {
        while (p != ids[p]) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }

        return p;
    }

    static private void union(int p, int q) {
        p = find(p);
        q = find(q);

        if (p == q) return;

        if (sizes[p] < sizes[q]) {
            ids[p] = q;
            sizes[q] += sizes[p];
        } else {
            ids[q] = p;
            sizes[p] += sizes[q];
        }
    }

    static private boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
