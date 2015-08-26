import java.io.*;
import java.util.*;

public class JessicasReadingProblem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        long[] array = new long[P];
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        for (int i = 0; i < P; i++) {
            array[i] = in.nextInt();
            map.put(array[i], 0);
        }
        int size = map.size();
        int count = 0;
        int l = 0, r = 0;
        int min = Integer.MAX_VALUE;

        while (r < array.length) {
            int rcount = map.get(array[r]) + 1;
            map.put(array[r++], rcount);
            if (rcount == 1) count++;

            while (count >= size) {
                min = Math.min(min, r - l);

                int lcount = map.get(array[l]) - 1;
                map.put(array[l++], lcount);
                if (lcount == 0) count--;
            }
        }
        System.out.println(min);
    }
}
