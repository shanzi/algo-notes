import java.util.Random;

class TrieNode {
    TrieNode zeroChild;
    TrieNode oneChild;
}

class Trie {
    TrieNode root = new TrieNode();

    void add(int n) {
        TrieNode p = root;
        for (int i = 30; i >= 0; i--) {
            if (((n >> i) & 1) == 1) {
                if (p.oneChild == null) {
                    p.oneChild = new TrieNode();
                }
                p = p.oneChild;
            } else {
                if (p.zeroChild == null) {
                    p.zeroChild = new TrieNode();
                }
                p = p.zeroChild;
            }
        }
    }
}

public class MaxXORSubSequence {
    public static int findMaxBruteForce(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            int m = 0;
            for (int j = i; j < array.length; j++) {
                m ^= array[j];
                max = Math.max(max, m);
            }
        }
        return max;
    }

    public static int findMaxTrie(int[] array) {
        Trie trie = new Trie();
        trie.add(0);
        int max = 0;
        int cur = 0;
        for (int i = 0; i < array.length; i++) {
            cur ^= array[i];
            TrieNode p = trie.root;
            int x = 0;
            for (int j = 30; j >= 0; j--) {
                if (((cur >> j) & 1) == 1) {
                    if (p.zeroChild != null) {
                        p = p.zeroChild;
                    } else {
                        x |= (1 << j);
                        p = p.oneChild;
                    }
                } else {
                    if (p.oneChild != null) {
                        x |= (1 << j);
                        p = p.oneChild;
                    } else {
                        p = p.zeroChild;
                    }
                }
            }
            max = Math.max(max, x ^ cur);
            trie.add(cur);
        }
        return max;
    }

    public static void main(String[] args) {
        int n = 50;
        int[] array = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(100000) + 1;
        }

        int a = findMaxBruteForce(array);
        int b = findMaxTrie(array);

        if (a == b) {
            System.out.println("Algorithm success!");
            System.out.println(String.format("----> %d == %d", a, b));
        } else {
            System.out.println("Algorithm failed!");
            System.out.println(String.format("----> %d != %d", a, b));
        }
    }
}
