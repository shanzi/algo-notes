import java.io.*;
import java.util.*;

public class MakingTheGrade {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] elev = new int[N];
        int[] elevsort = new int[N];
        for (int i = 0; i < N; i++) {
            elev[i] = in.nextInt();
            elevsort[i] = elev[i];
        }
        Arrays.sort(elevsort);

        int[] dp = new int[N];
        int[] min = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[j] = min[j] + Math.abs(elev[i] - elevsort[j]);
            }
            min[0] = dp[0];
            for (int j = 1; j < N; j++) {
                min[j] = Math.min(min[j - 1], dp[j]);
            }
        }

        int res = Integer.MAX_VALUE;
        for (int n : min) {
            res = Math.min(res, n);
        }
        
        System.out.println(res);
    }
}
