# Isomorphic Strings

Two strings `s` and `t` are isomorphic strings if and only if for any positions `i` and `j`
if the corresponding characters `s[i] = s[j]` then there must be `t[i] = t[j]` and **vice versa**.

```java
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        boolean[] marks = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (marks[i]) continue;
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && t.charAt(i) == t.charAt(j)) {
                    marks[j] = true;
                } else if (s.charAt(i) == s.charAt(j) || t.charAt(i) == t.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
}
```
