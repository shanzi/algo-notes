public class MagicalStringDiv1 {
    public int getLongest(String S) {
        int n = S.length();
        int rightCount = 0;
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == '<') {
                rightCount++;
            }
        }

        int leftCount = 0;
        int length = 0;
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == '>') {
                leftCount++;
            } else {
                rightCount--;
            }

            int len = Math.min(leftCount, rightCount) * 2;
            length = Math.max(length, len);
        }

        return length;
    }
}
