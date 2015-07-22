# Insert Interval

We should handle overlapping of different interval correctly. Two intervals `a` and `b` has overlap
if and only if `a.end > b.start` and `a.start < b.end` or vice versa.

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
public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        
        boolean inserted = false;
        
        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                result.add(interval);
            } else if (interval.start <= newInterval.end) {
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            } else {
                if (!inserted) {
                    result.add(newInterval);
                    inserted = true;
                }
                
                result.add(interval);
            }
        }
        
        if (!inserted) {
            result.add(newInterval);
        }
        
        return result;
    }
}
```
