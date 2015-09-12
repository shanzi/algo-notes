import java.io.*;
import java.util.*;

public class SortAScrambledItinerary {
    private static void print(Map<String, String> out, String start) {
        while (out.containsKey(start)) {
            String next = out.get(start);
            System.out.printf(" %s-%s", start, next);
            start = next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            HashMap<String, String> outMap = new HashMap<String, String>(N);
            HashSet<String> inSet = new HashSet<String>(N);
            for (int i = 0; i < N; i++) {
                String a = in.next(), b = in.next();
                outMap.put(a, b);
                inSet.add(b);
            }
            System.out.printf("Case #%d:", t + 1);
            for (String s: outMap.keySet()) {
                if (!inSet.contains(s)) {
                    print(outMap, s);
                    break;
                }
            }
        }
    }
}
