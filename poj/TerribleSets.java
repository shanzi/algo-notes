import java.io.*;
import java.util.*;

public class TerribleSets {
    private static void solve(int[] w, int[] h) {
        for (int i = 1; i < w.length; i++) {
            w[i] += w[i - 1];
        }

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);

        int max = 0;

        for (int i = 0; i < h.length; i++) {
            int height = h[i];
            int width = w[i];

            while (stack.size() > 1 && h[stack.peek()] >= height) {
                int midh = h[stack.pop()];
                int leftwidth = w[stack.peek() + 1];
                max = Math.max(max, midh * (width - leftwidth));
            }

            stack.push(i);
        }

        while (stack.size() > 1) {
            int midh = h[stack.pop()];
            int leftwidth = w[stack.peek() + 1];
            max = Math.max(max, midh * (w[w.length - 1] - leftwidth));
        }

        System.out.println(max);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            
            if (N < 0) return;

            int[] w = new int[N + 1];
            int[] h = new int[N];
            for (int i = 0; i < N; i++) {
                w[i + 1] = in.nextInt();
                h[i] = in.nextInt();
            }

            solve(w, h);
        }
    }
}
