public class PathGameDiv2 {
    public int calc(String[] board) {
        int n = board[0].length();
        int m = 0;
        int k = 0;
        int flag = 0;
        for (int i = 0; i < n; i++) {
            char ch1 = board[0].charAt(i);
            char ch2 = board[1].charAt(i);

            if (ch1 == '#') {
                if (flag == 2) m++;
                flag = 1;
                k++;
            } else if (ch2 == '#') {
                if (flag == 1) m++;
                flag = 2;
                k++;
            }
        }
        return n - m - k;
    }
}
