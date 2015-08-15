import java.io.*;
import java.util.*;

public class WirelessNetwork {
    static int[] ids;
    static int[] sizes;

    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
        String[] str = buf.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        long d = Long.parseLong(str[1]);
        d *= d;

        ids = new int[N];
        sizes = new int[N];

        for (int i = 0; i < N; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }

        long[] cx = new long[N];
        long[] cy = new long[N];
        
        for (int i = 0; i < N; i++) {
            str = buf.readLine().split(" ");
            cx[i] = Long.parseLong(str[0]);
            cy[i] = Long.parseLong(str[1]);
        }

        String line = null;
        HashSet<Integer> fixedComputers = new HashSet<Integer>(N);

        while ((line = buf.readLine()) != null && !line.isEmpty()) {
            str = line.split(" ");
            if (str[0].equals("O")) {
                int a = Integer.parseInt(str[1]) - 1;
                fixedComputers.add(a);

                for (Integer b: fixedComputers) {
                    if ((cx[a] - cx[b]) * (cx[a] - cx[b]) + (cy[a] - cy[b]) * (cy[a] - cy[b]) <= d) {
                        union(a, b);
                    }
                }

            } else {
                int a = Integer.parseInt(str[1]) - 1;
                int b = Integer.parseInt(str[2]) - 1;

                if (connected(a, b)) {
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("FAIL");
                }
            }
        }
    }

    private static int find(int p) {
        while (p != ids[p]) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }

    private static void union(int p, int q) {
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

    private static boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
