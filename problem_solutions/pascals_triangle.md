# Pascal's Triangle

To generate Pascal's triangle one row by one row is simple, we just make use of the last row
to generate next row. Each row of Pascal's triangle will starts and ends with 1, the other
number has the following formular:

{% math %}
N_{r}[i] = N_{r - 1}[i - 1] + N_{r - 1}[i]
{% endmath %}

Sample code:
```java
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>(numRows);
        if (numRows == 0) return res;
        ArrayList<Integer> one = new ArrayList<Integer>();
        one.add(1);
        res.add(one);
        
        for (int i = 2; i <= numRows; i++) {
            ArrayList<Integer> last = (ArrayList<Integer>)res.get(i - 2);
            ArrayList<Integer> current = new ArrayList<Integer>(i);
            current.add(1);
            for (int j = 1; j < i - 1; j++) {
                current.add(last.get(j - 1) + last.get(j));
            }
            current.add(1);
            res.add(current);
        }
        return res;
    }
}
```

If we need to generate any row by a given row number, we have to make use of the iteration formular.
At first we know:

{% math %}
N_{n}[i] = \left(\begin{array}{c}
n\\
i
\end{array}\right)
{% endmath %}

Which is the combination numbers of picking $$i$$ out of $$n$$ objects. So there will be a generate formular:

{% math %}
\left(\begin{array}{c}
n\\
i
\end{array}\right)=\frac{n-i+1}{i}\left(\begin{array}{c}
n\\
i-1
\end{array}\right)
{% endmath %}

We have to use the generate formular carefully to avoid integer range exceed and as after the first half
the sequence become lowing down, we also need to take care of the rounding down when performing integer division.

Sample code:

```java
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> res = new ArrayList<Integer>(rowIndex + 1);
        
        long n = rowIndex;
        long c = 1;
        res.add((int)c);
        for (int i = 1; i <= n; i++) {
            c = c * (n - i + 1) / i;
            res.add((int)c);
        }
        return res;
    }
}
```
