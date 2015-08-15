import java.io.*;
import java.util.*;

public class FindThemCatchThem {
    static int[] ids;
    static int[] rank;

    public static void main(String[] args) throws Exception {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
        int t = Integer.parseInt(buf.readLine());

        for (int i = 0; i < t; i++) {
            String str[] = buf.readLine().split(" ");  
            int n = Integer.parseInt(str[0]);  
            int m = Integer.parseInt(str[1]);

            ids = new int[n + 1];
            rank = new int[n + 1];

            for (int j = 0; j < ids.length; j++) {
                ids[j] = j;
            }

            for (int j = 0; j < m; j++) {
                str = buf.readLine().split(" ");  
                String op = str[0];
                int a = Integer.parseInt(str[1]);  
                int b = Integer.parseInt(str[2]);

                if (op.equals("D")) {
                    union(a, b);
                } else {
                    if(n == 2)  
                        System.out.println("In different gangs.");  
                    else if(find(a)==find(b)){  
                        if(rank[a] == rank[b])  
                            System.out.println("In the same gang.");  
                        else  
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

        ids[pid] = qid;
        rank[pid] = (rank[p] + rank[q] + 1) % 2;
    }

    private static int find(int p) {
        if (ids[p] == p) return p;

        int root = find(ids[p]);
        rank[p] = (rank[p] + rank[ids[p]]) % 2;
        return ids[p] = root;
    }
}
