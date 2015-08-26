import java.io.*;
import java.util.*;

public class FaceTheRightWay {
    private static int solve(int[] face, int k) {
        int[] flip = new int[face.length];
        int ksum = 0;
        int count = 0;
        for (int i = 0; i < face.length - k + 1; i++) {
            if (i >= k) ksum -= flip[i - k];
            if ((ksum + face[i]) % 2 == 1) {
                flip[i]++;
                count++;
            }
            ksum += flip[i];
        }

        for (int i = face.length - k + 1; i < face.length; i++) {
            if (i >= k) ksum -= flip[i - k];
            if ((ksum + face[i]) % 2 == 1) return Integer.MAX_VALUE;
            ksum += flip[i];
	    }
        return count;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] face = new int[N];
        for (int i = 0; i < N; i++) {
            if (in.next().equals("B")) {
                face[i] = 1;
            }
        }

        int min = Integer.MAX_VALUE;
        int mink = 1;
        for (int k = 1; k <= N; k++) {
            int res = solve(face, k);
            if (res < min) {
                min = res;
                mink = k;
            }
        }

        System.out.printf("%d %d\n", mink, min);
    }
}
