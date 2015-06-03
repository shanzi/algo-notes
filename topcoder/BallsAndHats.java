public class BallsAndHats {
    public int getHat(String hats, int numSwaps) {
        if (numSwaps == 0) {
            for (int i = 0; i < 3; i++) {
                if (hats.charAt(i) == 'o') return i;
            }
        }
        if (hats.charAt(0) == 'o' || hats.charAt(2) == 'o') {
            return numSwaps % 2;
        } else {
            return 1 - (numSwaps % 2);
        }
    }
}
