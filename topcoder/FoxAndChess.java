public class FoxAndChess {
    public String ableToMove(String begin, String end) {
        int a = 0; int b = 0;
        int n = begin.length();
        while(a < n && begin.charAt(a) == '.') a++;
        while(b < n && end.charAt(b) == '.') b++;

        while (a < n && b < n) {
            if (begin.charAt(a) != end.charAt(b)) return "Impossible";
            if (begin.charAt(a) == 'L' && a < b) return "Impossible";
            if (begin.charAt(a) == 'R' && a > b) return "Impossible";

            a++; b++;
            while(a < n && begin.charAt(a) == '.') a++;
            while(b < n && end.charAt(b) == '.') b++;
        }

        if (a == n && b == n) return "Possible";
        else return "Impossible";
    }
}
