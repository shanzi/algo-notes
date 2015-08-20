import java.io.*;
import java.util.*;

public class AggresiveCows {
    private static boolean valid(int[] positions, int C, int L) {
        int l = positions[0];
        C--;
        for (int p : positions) {
            if (p >= l + L) {
                l = p;
                if ((--C) == 0) break;
            }
        }
        return C <= 0;
    }
    private static void solve(int[] positions, int C) {
        int l = 0, u = positions[positions.length - 1] - positions[0];
        while (l <= u) {
            int mid = (l + u) / 2;
            if (valid(positions, C, mid)) l = mid + 1;
            else u = mid - 1;
        }
        System.out.println(u);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), C = in.nextInt();
        int[] pos = new int[N];
        for (int i = 0; i < N; i++) pos[i] = in.nextInt();
        Arrays.sort(pos);
        solve(pos, C);
    }
}
