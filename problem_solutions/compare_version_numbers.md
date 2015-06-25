# Compare Version Numbers

At first translate string into a list of integers. Then compare the integers one by one in parallel.
When one of the list is not long enough, use zero as placeholder.

```java
public class Solution {
    private ArrayList<Integer> getNums(String version) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int current = 0;
        for (int i = 0; i < version.length(); i++) {
            int ch = version.charAt(i);
            if (ch >= '0' && ch <= '9') {
                current = (current * 10) + (ch - '0');
            } else if (ch == '.') {
                res.add(current);
                current = 0;
            }
        }
        res.add(current);
        return res;
    }
    public int compareVersion(String version1, String version2) {
        ArrayList<Integer> versionNum1 = getNums(version1);
        ArrayList<Integer> versionNum2 = getNums(version2);
        
        int n = Math.max(versionNum1.size(), versionNum2.size());
        
        for (int i = 0; i < n; i++) {
            int a = i < versionNum1.size() ? versionNum1.get(i) : 0;
            int b = i < versionNum2.size() ? versionNum2.get(i) : 0;
            if (a > b) return 1;
            else if (a < b) return -1;
        }
        
        return 0;
        
    }
}
```
