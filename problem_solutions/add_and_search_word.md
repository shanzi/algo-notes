# Add and Search Word

This is a data structure design problem. The answer is [Trie](https://en.wikipedia.org/wiki/Trie).

```java
// Problem: Add and Search Word - Data structure design 
//
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean end = false;
}

public class WordDictionary {
    TrieNode root = new TrieNode();
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (p.children[ch - 'a'] == null) {
                p.children[ch - 'a'] = new TrieNode();
            }
            
            p = p.children[ch - 'a'];
        }
        p.end = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(root, word, 0);
    }
    
    private boolean search(TrieNode p, String word, int idx) {
        if (p == null) return false;
        
        for (int i = idx; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == '.') {
                for (int j = 0; j < p.children.length; j++) {
                    if (search(p.children[j], word, i + 1)) return true;
                }
                return false;
            } else {
                p = p.children[ch - 'a'];
                if (p == null) return false;
            }
        }
        
        return p.end;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
```
