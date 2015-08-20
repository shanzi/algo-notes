import java.io.*;
import java.util.*;

public class RiverHopscotch {
    private static boolean canRemove(int[] distance, int L, int M) {
        int l = 0;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] < l + L) {
                if ((--M) < 0) break;
            } else {
                l = distance[i];
            }
        }
        return M >= 0;
    }
    private static void solve(int[] distance, int M) {
        int l = 0, u = distance[distance.length - 1];
        while (l <= u) {
            int mid = (l + u) / 2;
            if (canRemove(distance, mid, M)) l = mid + 1;
            else u = mid - 1;
        }
        System.out.println(u);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt(), N = in.nextInt(), M = in.nextInt();
        int[] distance = new int[N + 2];
        distance[0] = 0; distance[N + 1] = L;
        for (int i = 1; i <= N; i++) distance[i] = in.nextInt();
        Arrays.sort(distance);
        solve(distance, M);
    }
}
