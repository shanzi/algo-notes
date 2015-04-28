public class LongLongTripDiv2 {
    public String isAble(long D, int T, int B) {
        long b = (D - T) % (B - 1);
        if (b != 0) {
            return "Impossible";
        }
        b = (D - T) % (B - 1);
        long a = T - b;
        if (a >= 0 && b >= 0) {
            return "Possible";
        } else {
            return "Impossible";
        }
    }
}
