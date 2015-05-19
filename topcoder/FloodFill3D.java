public class FloodFill3D {
    public long countBlack(String[] SArray,
                           String[] TArray,
                           String[] UArray) {
        String s = concat(SArray);
        String t = concat(TArray);
        String u = concat(UArray);

        long white = solve(s, t, u, 'o') + solve(s, t, u, 'x');
        long sl = s.length();
        long tl = t.length();
        long ul = u.length();
        return sl * tl * ul - white;
    }

    String concat(String[] array) {
        StringBuffer sbuf = new StringBuffer();
        for (String s : array) {
            sbuf.append(s);
        }
        return sbuf.toString();
    }

    long count(String s, char c) {
        long ret = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) ret++;
        }
        return ret;
    }

    long countMiddle(String s, char c) {
        int i = 0;
        int j = s.length() - 1;

        while(i <= j && s.charAt(i) == c) i++;
        while(j >= 0 && s.charAt(j) == c) j--;

        long ret = 0;

        for (; i < j; i++) {
            if (s.charAt(i) == c) ret++;
        }
        return ret;
    }

    long solve(String s, String t, String u, char c) {
        return count(s, c) * count(t, c) * count(u, c) - countMiddle(s, c) * countMiddle(t, c) * countMiddle(u, c);
    }
}

