import java.io.*;
import java.util.*;

public class Sorting {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            boolean[] labels = new boolean[N];
            ArrayList<Integer> alexBooks = new ArrayList<Integer>(N);
            ArrayList<Integer> bobBooks = new ArrayList<Integer>(N);
            for (int i = 0; i < N; i++) {
                int book = in.nextInt();
                if ((book & 1) == 1) alexBooks.add(book);
                else bobBooks.add(book);
                labels[i] = (book & 1) == 1;
            }
            Collections.sort(alexBooks);
            Collections.sort(bobBooks);
            System.out.printf("Case #%d:", t + 1);
            int a = 0;
            int b = bobBooks.size() - 1;
            for(boolean l : labels){
                if (l) System.out.printf(" %d", alexBooks.get(a++));
                else System.out.printf(" %d", bobBooks.get(b--));
            }
            System.out.println();
        }
    }
}
