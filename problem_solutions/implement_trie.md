# Implement Trie

Trie is a useful data structure. This problem is in fact comes from LeetCode's
[Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/), but I think
it is to common to be put under the LeetCode section.

A general implementation of Trie is like:
```java


class TrieNode {
    boolean end = false;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    
    public TrieNode() {
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p.children.containsKey(c)) {
                p = p.children.get(c);
            } else {
                TrieNode node = new TrieNode();
                p.children.put(c, node);
                p = node;
            }
        }
        p.end = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            p = p.children.get(word.charAt(i));
            if (p == null) return false;
        }
        return p.end;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            p = p.children.get(prefix.charAt(i));
            if (p == null) return false;
        }
        return true;
    }
}
```

Note that we save the character of a node in its parent in the `HashMap` instead of the node itself.
So the `root` node do not represent any characters. If the charset is limited to letters, we can replace
the `HashMap` in node with an array of `char` just like the Trie used in
[Add and Search Word](/problem_solutions/add_and_search_word.md) 


