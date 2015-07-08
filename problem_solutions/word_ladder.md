# Word Ladder

This is a general BFS question, but as to pass the tests in time, we have to do a few optimizations.
There are several ways to implement the algorithm, but we must note that:

1. There are two ways to find neighbors. One is process all words in the `wordDict` once and find its
neighbor, another is change one character in current word and check if it is in `wordDict`. The latter
one seems faster in this problem.
2. Usually, when doing BFS on a graph, we can either visit the node when it is polled out of the queue
or added into the queue. It is usually doesn't matter if the graph is small as both two ways gives correct
answer. If you'd like to use the former one, don't forget to check if it has already been already visited,
and avoid adding this neighbors to queue. Using the latter one may avoid some nodes from added into the queue
repeatedly.

```java
public class Solution {
    
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        
        if (!wordDict.contains(beginWord) || !wordDict.contains(endWord)) return 0;
        
        int length = 1;
        
        HashSet<String> visited = new HashSet<String>();
        LinkedList<String> queue = new LinkedList<String>();
        
        queue.add(beginWord);
        queue.add("");
        
        String w, nw;
        char[] ws;
        
        while (!queue.isEmpty()) {
            w = queue.pollFirst();
            
            if (w.isEmpty()) {
                length++;
                if (!queue.isEmpty()) queue.addLast("");
                continue;
            }
            
            if (visited.contains(w)) continue;
            
            visited.add(w);
            
            ws = w.toCharArray();
            for (int i = 0; i < ws.length; i++) {
                char ch = ws[i];
                for (char c='a'; c <= 'z'; c++) {
                    if (c == ch) continue;
                    ws[i] = c;
                    nw = String.valueOf(ws);
                    if (nw.equals(endWord)) return length + 1;
                    if (wordDict.contains(nw) && !visited.contains(nw)) {
                        queue.addLast(nw);
                    }
                }
                ws[i] = ch;
            }
        }
        
        return 0;
    }
}
```
