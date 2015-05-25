import java.util.*;

public class FoxAndMp3Easy {
    private void add(ArrayList<Integer> list, int k, int n) {
        if (list.size() > Math.min(50, n)) {
            return;
        }

        for (int i = 0; i <= 9; i++) {

            int l = k + i;

            if (l == 0) {
                continue;
            }

            if (l > n) {
                return;
            }

            list.add(l);
            add(list, l * 10, n);
        }
    }

    public String[] playList(int n) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        add(list, 0, n);
        int limit = Math.min(50, n);
        String[] ret = new String[limit];

        for (int i = 0; i < limit; i++) {
            ret[i] = String.valueOf(list.get(i)) + ".mp3";
        }

        return ret;
    }
}
