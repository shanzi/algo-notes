import java.io.*;
import java.util.*;

class UnionFind {
    int[] ids;
    int[] rank;

    public UnionFind(int N) {
        ids = new int[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            ids[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        if (p == ids[p]) return p;
        else return ids[p] = find(ids[p]);
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid == qid) return; // IMPORTANT!

        if (rank[pid] < rank[qid]) {
            ids[pid] = qid;
        } else {
            ids[qid] = pid;
            if (rank[pid] == rank[qid]) rank[pid]++;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}

public class FoodChain {
    public static void main(String[] args) throws Exception {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
        String[] str = buf.readLine().split(" ");
        int N = Integer.parseInt(str[0]), K = Integer.parseInt(str[1]);
        int wrongCount = 0;

        UnionFind uf = new UnionFind(N * 3);

        for (int i = 0; i < K; i++) {
            str = buf.readLine().split(" ");
            int D = Integer.parseInt(str[0]), a = Integer.parseInt(str[1]) - 1, b = Integer.parseInt(str[2]) - 1;

            if (a < 0 || a >= N || b < 0 || b >= N) {
                wrongCount++;
                continue;
            }

            if (D == 1) {
                if (uf.connected(a, b + N) || uf.connected(a, b + 2 * N)) {
                    wrongCount++;
                } else {
                    uf.union(a, b);
                    uf.union(a + N, b + N);
                    uf.union(a + 2 * N, b + 2 * N);
                }
            } else {
                if (uf.connected(a, b) || uf.connected(a, b + 2 * N)) {
                    wrongCount++;
                } else {
                    uf.union(a, b + N);
                    uf.union(a + N, b + 2 * N);
                    uf.union(a + 2 * N, b);
                }
            }
        }

        System.out.println(wrongCount);
    }
}
