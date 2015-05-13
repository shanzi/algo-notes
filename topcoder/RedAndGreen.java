public class RedAndGreen {
    public int minPaints(String row) {
        int leftGreen = 0;
        int rightRed = 0;

        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == 'R') {
                rightRed++;
            }
        }

        int minp = leftGreen + rightRed;
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == 'G') {
                leftGreen++;
            }
            else if (row.charAt(i) == 'R') {
                rightRed--;
            }

            minp = Math.min(minp, leftGreen + rightRed);
        }

        return minp;
    }
}
