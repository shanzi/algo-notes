import java.io.*;
import java.util.*;

public class BadHairDay {
    private static void solve(int[] heights) {
        Stack<Integer> heightStack = new Stack<Integer>();
        heightStack.push(heights.length);
        
        long count = 0;

        for (int i = heights.length - 1; i >= 0; i--) {
            int height = heights[i];

            while (heightStack.size() > 1 && heights[heightStack.peek()] < height) {
                heightStack.pop();
            }

            count += heightStack.peek() - i - 1;
            heightStack.push(i);
        }

        System.out.println(count);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            int[] heights = new int[N];

            for (int i = 0; i < N; i++) {
               heights[i] = in.nextInt(); 
            }

            solve(heights);
        }
    }
}
