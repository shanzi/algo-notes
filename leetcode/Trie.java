// Problem: Implement Trie 
import java.util.*;

class TrieNode {
    // Initialize your data structure here.
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

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
