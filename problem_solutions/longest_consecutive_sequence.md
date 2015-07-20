# Longest Consecutive Sequence

We use range merging strategy when processing the items:

1. A single item produces a range of `[n, n]`.
2. If there is an adjoined range before or after the item, merge the item into the range
3. If both before and after range can be merged, we merge the three into a whole long range

Don't forget to avoid process a same item twice if there are duplicated items in the array
or it may produce wrong result.

```java
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> startEndMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> endStartMap = new HashMap<Integer, Integer>();
        HashSet<Integer> dedupSet = new HashSet<Integer>();
        int l, r;
        
        for (int n : nums) {
            if (dedupSet.contains(n)) continue;
            dedupSet.add(n);
            
            if (startEndMap.containsKey(n + 1) && endStartMap.containsKey(n - 1)) {
                l = endStartMap.get(n - 1);
                r = startEndMap.get(n + 1);
                startEndMap.put(l, r);
                endStartMap.put(r, l);
                startEndMap.remove(n + 1);
                endStartMap.remove(n - 1);
            } else if (startEndMap.containsKey(n + 1)) {
                l = n;
                r = startEndMap.get(n + 1);
                startEndMap.put(l, r);
                endStartMap.put(r, l);
                startEndMap.remove(n + 1);
            } else if (endStartMap.containsKey(n - 1)) {
                l = endStartMap.get(n - 1);
                r = n;
                startEndMap.put(l, r);
                endStartMap.put(r, l);
                endStartMap.remove(n - 1);
            } else {
                startEndMap.put(n, n);
                endStartMap.put(n, n);
            }
        }
        int max = 0;
        
        for (Map.Entry<Integer, Integer> kv : startEndMap.entrySet()) {
            max = Math.max(max, kv.getValue() - kv.getKey() + 1);
        }
        
        return max;
    }
}
```
