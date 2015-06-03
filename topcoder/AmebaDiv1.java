import java.util.*;

public class AmebaDiv1 {
    public int count(int[] X) {
        HashSet<Integer> set = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (int v: X) {
            set.add(v);
        }
        for (int v : set) {
            for (int j = 0; j < X.length; j++) {
                if (X[j] == v) {
                    v *= 2;
                }
            }
            if (set.contains(v)) {
                set2.add(v);
            }
        }
        return set.size() - set2.size();
    }
}
