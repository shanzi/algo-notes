import java.io.*;
import java.util.*;

public class FindThemCatchThem2 {
    static int[] ids;
    static int[] sizes;

    public static void main(String[] args) throws Exception {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
        int t = Integer.parseInt(buf.readLine());

        for (int i = 0; i < t; i++) {
            String str[] = buf.readLine().split(" ");  
            int n = Integer.parseInt(str[0]);  
            int m = Integer.parseInt(str[1]);

            ids = new int[n * 2];
            sizes = new int[n * 2];

            for (int j = 0; j < ids.length; j++) {
                ids[j] = j;
                sizes[i] = 1;
            }

            for (int j = 0; j < m; j++) {
                str = buf.readLine().split(" ");  
                String op = str[0];
                int a = Integer.parseInt(str[1]) - 1;
                int b = Integer.parseInt(str[2]) - 1;

                if (op.equals("D")) {
                    union(a, b + n);
                    union(a + n, b);
                } else {
                    if(connected(a, b))  
                        System.out.println("In the same gang.");  
                    else if(connected(a, b + n)){  
                        System.out.println("In different gangs.");  
                    }else  
                        System.out.println("Not sure yet."); 
                }
            }
        }
    }

    private static void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (sizes[pid] < sizes[qid]) {
            ids[pid] = qid;
            sizes[qid] += sizes[pid];
        } else {
            ids[qid] = pid;
            sizes[pid] += sizes[qid];
        }
    }

    private static int find(int p) {
        while (p != ids[p]) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }

    private static boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
