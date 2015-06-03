public class IncrementAndDoubling {
    public int getMin(int[] desiredArray) {
        int maxDoubling = 0;
        int totalInc = 0;
        for (int n : desiredArray) {
            int doubling = 0;
            while (n > 0) {
                if (n % 2 == 1) {
                    totalInc++;
                    n--;
                } else {
                    n /= 2;
                    doubling++;
                }
            }
            if (doubling > maxDoubling) {
                maxDoubling = doubling;
            }
        }
        return totalInc + maxDoubling;
    }
}
