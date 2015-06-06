public class IsomorphicStrings {
    // The two string is isomorphic if and only if for any i, j
    //   1. if s[i] = s[j] then t[i] = t[j]
    //   2. if s[i] != s[j] then t[i] != t[j]
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        boolean[] marks = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (marks[i]) continue;
            marks[i] = true;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (t.charAt(j) != t.charAt(i)) return false;
                    marks[j] = true;
                } else {
                    if (t.charAt(j) == t.charAt(i)) return false;
                }
            }
        }
        return true;
    }
}
