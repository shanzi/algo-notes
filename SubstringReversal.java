public class SubstringReversal {
    public int[] solve(String S) {
        int[] res = {0, 0};
        char[] str = S.toCharArray();
        int[] min = new int[str.length];
        min[min.length - 1] = min.length - 1;
        for (int i = min.length - 2; i >= 0; i--) {
            if (str[i] <= str[min[i + 1]]) {
                min[i] = i;
            } else {
                min[i] = min[i + 1];
            }
        }

        for (int i = 0; i < min.length; i++) {
            if (i < min[i]) {
                res[0] = i;
                res[1] = min[i];
                String best = S;
                for (int j = min[i]; j < min.length; j++) {
                    String ns = reverse(S, i, j);
                    if (ns.compareTo(best) < 0) {
                        best = ns;
                        res[1] = j;
                    }
                }
                return res;
            }
        }

        return res;
    }

    private String reverse(String s, int start, int end) {
        StringBuffer sb = new StringBuffer(s.substring(start, end + 1));
        sb.reverse().append(s.substring(end + 1));
        return s.substring(0, start) + sb.toString();
    }
}

