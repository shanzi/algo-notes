public class PalindromicSubstringsDiv2 {
    private String concatenateStrings(String[] A, String[] B) {
        StringBuffer buf = new StringBuffer();
        for (String s : A) {
            buf.append(s);
        }
        for (String s : B) {
            buf.append(s);
        }

        return buf.toString();
    }
    public int count(String[] S1, String[] S2) {
        String S = concatenateStrings(S1, S2);
        int n = S.length();
        int countN = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i - j < 0 || i + j >= n) break;
                if (S.charAt(i - j) == S.charAt(i + j)) countN++;
                else break;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i - j - 1 < 0 || i + j >= n) break;
                if (S.charAt(i - j - 1) == S.charAt(i + j)) countN++;
                else break;
            }
        }

        return countN;
    }
}
