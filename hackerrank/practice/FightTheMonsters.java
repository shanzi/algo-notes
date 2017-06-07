import java.io.*;
import java.util.*;

public class FightTheMonsters {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long h = in.nextLong(), t = in.nextLong();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        Arrays.sort(arr);
        long count = 0;
        for (int i = 0; i < n; i++) {
            long c = arr[i] / h + (arr[i] % h > 0 ? 1 : 0); 
            if (t >= c) {
                t -= c;
                count += 1;
            } else {
                break;
            }
        }
        System.out.println(count);
    }
}
