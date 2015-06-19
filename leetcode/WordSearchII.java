import java.util.*;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean end = false;
}

public class WordSearchII {
    TrieNode root = null;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};
    
    private void makeTrie(String[] words) {
        root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            int i = 0;
            while (i < w.length()) {
                char ch = w.charAt(i);
                if (p.children[ch - 'a'] == null) {
                    p.children[ch - 'a'] = new TrieNode();
                }
                p = p.children[ch - 'a'];
                i++;
            }
            p.end = true;
        }
    }
    
    private void traverseMatrix(char[][] board, int x, int y, TrieNode root, StringBuilder sbd, List<String> result) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        char ch = board[x][y];
        
        if (ch == '.') return;
        TrieNode child = root.children[ch - 'a'];
        
        if (child == null) return;
        else {
            sbd.append(ch);
            board[x][y] = '.';
            if (child.end) {
                child.end = false; // avoid found a word twice
                result.add(sbd.toString());
            }

            for (int i = 0; i < 4; i++) {
                // cell can not be reused in a single word, but one cell can be shared by different words
                traverseMatrix(board, x + dx[i], y + dy[i], child, sbd, result);
            }
            board[x][y] = ch;
            sbd.deleteCharAt(sbd.length() - 1);
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        makeTrie(words);
        ArrayList<String> result = new ArrayList<String>();
        StringBuilder sbd = new StringBuilder();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                traverseMatrix(board, i, j, root, sbd, result);
            }
        }
        return result;
    }
}
