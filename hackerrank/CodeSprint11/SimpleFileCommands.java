import java.io.*;
import java.util.*;

public class SimpleFileCommands {
    static HashMap<String, PriorityQueue<Integer>> map = new HashMap<String, PriorityQueue<Integer>>();

    private static String cleanedName(String filename) {
        for (int i = 0; i < filename.length(); i++) {
            if (filename.charAt(i) == '(') {
                return filename.substring(0, i);
            }
        }
        return filename;
    }

    private static int nameNumber(String filename) {
        for (int i = 0; i < filename.length(); i++) {
            if (filename.charAt(i) == '(') {
                return Integer.valueOf(filename.substring(i + 1, filename.length() - 1));
            }
        }
        return 0;
    }

    private static String crtFile(String filename) {
        if (map.containsKey(filename)) {
            PriorityQueue<Integer> que = map.get(filename);
            int last = que.poll();

            if (que.isEmpty()) que.offer(last + 1);
            if (last == 0) return filename;
            else return String.format("%s(%d)", filename, last);
        } else {
            PriorityQueue<Integer> que = new PriorityQueue<Integer>();
            que.offer(1);
            map.put(filename, que);

            return filename;
        }
    }

    private static String delFile(String filename) {
        String cfn = cleanedName(filename);
        int number = nameNumber(filename);

        PriorityQueue<Integer> que = map.get(cfn);
        que.offer(number);

        return filename;
    }

    private static String rnmFile(String filename, String newName) {
        delFile(filename);
        return crtFile(cleanedName(newName));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int Q = in.nextInt();
        for (int q = 0; q < Q; q++) {
            String op = in.next();
            String filename = in.next();

            if (op.equals("crt")) {
                System.out.printf("+ %s\n", crtFile(filename));
            } else if (op.equals("del")) {
                System.out.printf("- %s\n", delFile(filename));
            } else if (op.equals("rnm")) { 
                System.out.printf("r %s -> %s\n", filename, rnmFile(filename, in.next()));
            }
        }
    }
}
