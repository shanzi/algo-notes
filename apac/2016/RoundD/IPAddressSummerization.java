import java.io.*;
import java.util.*;

class BinaryTrieNode {
    BinaryTrieNode zero = null; 
    BinaryTrieNode one = null; 
    boolean isEnd = false;
}

public class IPAddressSummerization {
    private static BinaryTrieNode root = null;
    private static BinaryTrieNode put(BinaryTrieNode root, int ip, int m) {
        if (root == null) root = new BinaryTrieNode();
        if (m == 0) {
            root.isEnd = true;
            root.zero = null;
            root.one = null;
        }
        else if (!root.isEnd) {
            if (ip < 0) {
                root.one = put(root.one, ip << 1, m - 1);
            } else {
                root.zero = put(root.zero, ip << 1, m - 1);
            }
        }

        if (root.zero != null && root.zero.isEnd && root.one != null && root.one.isEnd) {
            root.isEnd = true;
            root.zero = null;
            root.one = null;
        }

        return root;
    }
    private static void normalize(String s) {
        String[] comps = s.split("\\D");
        int ip = 0;
        for (int i = 0; i < 4; i++) {
            ip = (ip << 8) | Integer.parseInt(comps[i]);
        }
        int m = Integer.parseInt(comps[4]);
        root = put(root, ip, m);
    }
    private static void output(int ip, int m) {
        int a = (ip >> 24) & ((1 << 8) - 1);
        int b = (ip >> 16) & ((1 << 8) - 1);
        int c = (ip >> 8) & ((1 << 8) - 1);
        int d = ip & ((1 << 8) - 1);
        System.out.printf("%d.%d.%d.%d/%d\n", a, b, c, d, m);
    }
    private static void printIP(BinaryTrieNode node, int ip, int m) {
        if (node.isEnd) {
            output(ip, m);
        } else {
            if (node.zero != null) printIP(node.zero, ip, m + 1);
            if (node.one != null) printIP(node.one, ip | (1 << (31 - m)), m + 1);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            root = null;
            int N = in.nextInt();
            for (int i = 0; i < N; i++) {
                normalize(in.next());
            }
            System.out.printf("Case #%d:\n", t + 1);
            printIP(root, 0, 0);
        }
    }
}
