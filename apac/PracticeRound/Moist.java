import java.io.*;
import java.util.*;

public class Moist {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int T = in.nextInt();

            for (int t = 0; t < T; t++) {
                int m = in.nextInt();
                String max = null;
                int fee = 0;
                in.nextLine(); // Skip an empty line
                for (int i = 0; i < m; i++) {
                    String s = in.nextLine();
                    if (max == null || max.compareTo(s) < 0) {
                        max = s;
                    } else if (max.compareTo(s) > 0) {
                        fee++;
                    }
                }

                System.out.printf("Case #%d: %d\n", t + 1, fee);
            }
        }
    }
}
