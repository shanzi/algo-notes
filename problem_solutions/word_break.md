# Work Break

This is a dynamic programing problem. A string `s` can be split into words if and only if
`s[i..end]` is in dictionary and `s[start..i]` is able to be split into words.

```java
public class WordBreak {
    public boolean wordBreak(String s, Set<String> wordDict) {
        int len = 0;
        for (String word: wordDict) {
            len = Math.max(len, word.length());
        }
        
        boolean[] canSplit = new boolean[s.length() + 1];
        canSplit[0] = true;
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= i - len && j >= 0; j--) {
                if (canSplit[j]) {
                    if(wordDict.contains(s.substring(j, i))) {
                        canSplit[i] = true;
                        break;
                    }
                }
            }
        }
        
        return canSplit[s.length()];
    }
}
```

## Reserve words

In [Word Break II](https://leetcode.com/problems/word-break/), you are asked to return all possible
splits. The time cost will be a bit high if we do that like what we do above.
To avoid iterating over each position in string `s` we can use a recursive way to get result
and add a cache `HashMap` to save intermediate results.

We can either start from the begining or from the end. Following code shows the latter.

```java
public class WordBreakII {
    private List<String> wordBreak(String s, Set<String> wordDict, int len, int pos, HashMap<Integer, List<String>> cache) {
        if (cache.containsKey(pos)) return cache.get(pos);
        
        ArrayList<String> res = new ArrayList<String>();
        if (pos == 0) {
            res.add("");
            cache.put(0, res);
            return res;
        }
        
        for (int i = pos - 1; i >= 0 && i >= pos - len; i--) {
            String word = s.substring(i, pos);
            if (wordDict.contains(word)) {
                List<String> sub = wordBreak(s, wordDict, len, i, cache);
                for (String w: sub) {
                    if (w.isEmpty()) res.add(word);
                    else res.add(w + " " + word);
                }
            }
        }
        
        cache.put(pos, res);
        return res;
    }
    
    public List<String> wordBreak(String s, Set<String> wordDict) {
        int len = 0;
        for(String w: wordDict) len = Math.max(len, w.length());
        HashMap<Integer, List<String>> cache = new HashMap<Integer, List<String>>();
        return wordBreak(s, wordDict, len, s.length(), cache);
    }
}
```
