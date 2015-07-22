# Merge Intervals

At first we sort the intervals by their start property with `Collections.sort`,
then we merge next interval to last one by one.

```java
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval> () {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        ArrayList<Interval> result = new ArrayList<Interval>();
        
        for (Interval interval : intervals) {
            if (result.isEmpty()) {
                result.add(interval);
                continue;
            }
            
            Interval last = result.get(result.size() - 1);
            if (last.end < interval.start) {
                result.add(interval);
            } else {
                last.end = Math.max(last.end, interval.end);
            }
        }
        
        return result;
    }
}
```
