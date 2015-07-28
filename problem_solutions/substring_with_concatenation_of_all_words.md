# Substring with Concatenation of All Words

The key to this problem is that we can split the string `s` into groups. Let's say the length
of word in word dictionary is `l`, then we can first find start index with the string splited at
`0, l, 2 * l, 3 * l, ...`. Then we move all these indexes one step forward and try to find
start index with words start at `1, l + 1, 2 * l + 1, 3 * l + 1, ...`. We keep moving these indexes
until we meet `l - 1, 2 * l - 1, 3 * l - 1, 4 * l - 1, ...`.

For each of these groups, we can apply sliding window method. Let `a` and `b` be two index,
we always keeps the words between `a` and `b` are in the dictionary and add `a` to result if
substring between `a` and `b` is a concatenations of all words. As we will visit every index
in the string once so even we have two loops in the program, the time complexity will be
$$O(N)$$.

```java
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (words.length == 0) return result;
        
        int l = words[0].length();
        int n = words.length;
        
        if (l == 0) return result;
        if (s.length() < l * n) return result;
        
        HashMap<String, Integer> wordsCount = new HashMap<String, Integer>();
        
        for (String w : words) {
            if (wordsCount.containsKey(w)) {
                wordsCount.put(w, wordsCount.get(w) + 1);
            } else {
                wordsCount.put(w, 1);
            }
        }
        
        String[] splited = new String[s.length() - l + 1];
        
        for (int i = 0; i < splited.length; i++) {
            splited[i] = s.substring(i, i + l);
        }
        
        for (int i = 0; i < l; i++) {
            int count = 0;
            int a = i;
            HashMap<String, Integer> partCount = new HashMap<String, Integer>();
            
            for (int b = i; b < splited.length; b += l) {
                String word = splited[b];
                
                if (!wordsCount.containsKey(word)) {
                    a = b + l;
                    partCount.clear();
                    count = 0;
                    continue;
                }
                
                if (partCount.containsKey(word)) {
                    partCount.put(word, partCount.get(word) + 1);
                    count++;
                } else {
                    partCount.put(word, 1);
                    count++;
                }
                
                while (partCount.get(word) > wordsCount.get(word)) {
                    String removeWord = splited[a];
                    partCount.put(removeWord, partCount.get(removeWord) - 1);
                    count--;
                    a += l;
                }
                
                if (count == words.length) {
                    result.add(a);
                }
            }
        }
        
        return result;
    }
}
```
