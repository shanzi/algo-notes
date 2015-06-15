import java.util.*;
/*
 * One bin can only contains 1, 2 or 3 items. 
 * From the largest to smallest item, we have:
 *
 * 1. If this item can not be put with any other item, then it will be a bin with a single item.
 * 2. If this item can be put with another item, we choose heaviest one.
 * 2. It contains 3 items if and only if the three items are all 100, which means the largest one is 100.
 *
 * So although BinPacking problem is generally NP-Hard, but we have a quick solution for this problem
 *
 */

public class BinPacking {
    public int minBins(int[] item) {
        Arrays.sort(item);
        int count = 0;

        while (true) {
            boolean found = false;
            for (int i : item) {
                if (i > 0) {
                    found = true;
                    break;
                }
            }

            if (!found) break;

            int space = 300;
            for (int i = item.length - 1; i >= 0; i--) {
                if (item[i] > 0 && item[i] <= space) {
                    space -= item[i];
                    item[i] = 0;
                }
            }
            count++;
        }

        return count;
    }
}
