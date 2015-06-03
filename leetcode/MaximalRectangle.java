import java.util.*;
public class MaximalRectangle {
    private int getLineMax(int[] array) {
        Stack<Integer> indexStack = new Stack<Integer>();
        indexStack.push(-1);
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            int n = array[i];
            while (indexStack.size() > 1 && array[indexStack.peek()] >= n) {
                int mid = indexStack.pop();
                max = Math.max(max, array[mid] * (i - indexStack.peek() - 1));
            }
            indexStack.push(i);
        }
        while (indexStack.size() > 1) {
            int mid = indexStack.pop();
            max = Math.max(max, array[mid] * (array.length - indexStack.peek() - 1));
        }
        return max;
    }
    
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] uppercount = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    uppercount[i][j] = 1;
                    if (i > 0) uppercount[i][j] += uppercount[i - 1][j];
                }
            }
        }
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            max = Math.max(max, getLineMax(uppercount[i]));
        }
        return max;
    }
}
