# Max Points on a Line

To speed up calculation, at first we sort the points with two coordinates. To find number of max point
on a line, we at first pick one point and then find another point which is not at the same position
of the former one, then we find all points on the line made by these two points.

In this progress, as all points on a line won't on another different line through the first point at the same
time, so we uses a check array to mark points processed. Thus the the algorighm will run faster.
In the worst case the time cost of this algorithm will be $$O(N^3)$$. But in averge it can be as good as
$$O(N^2)$$.

```java
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class MaxPoints {
    private boolean samePoint(Point a, Point b) {
        return a.x == b.x && a.y == b.y;
    }
    
    private boolean sameLine(Point a, Point b, Point c) {
        return (b.y - a.y)*(c.x - a.x) == (c.y - a.y)*(b.x - a.x);
    }
    
    public int maxPoints(Point[] points) {
        if (points.length < 3) return points.length;
        
        Arrays.sort(points, new Comparator<Point>() {
           public int compare(Point a, Point b) {
               if (a.x != b.x) return a.x - b.x;
               else return a.y - b.y;
           } 
        });
        
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            boolean[] checked = new boolean[points.length];
            int sameCount = 1;
            int diffCount = 0;
            
            while (i + 1 < points.length && samePoint(points[i], points[i + 1])) {
                i++;
                sameCount++;
            }
            
            for (int j = i + 1; j < points.length; j++) {
                if (checked[j]) continue;
                diffCount = 0;
                
                for (int k = j; k < points.length; k++) {
                    if (sameLine(points[i], points[j], points[k])) {
                        diffCount++;
                        checked[k] = true;
                    }
                }
                
                max = Math.max(max, sameCount + diffCount);
            }
            
            max = Math.max(max, sameCount + diffCount);
        }
        
        return max;
    }
}
```
