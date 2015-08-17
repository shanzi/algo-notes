import java.io.*;
import java.util.*;

public class Layout {
    private static void solve(int N, int[] AL, int[] BL, int[] DL, int[] AD, int[] BD, int[] DD) {
        int[] d = new int[N];
        Arrays.fill(d, Integer.MAX_VALUE);

        d[0] = 0;

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N - 1; i++) {
                if (d[i + 1] < Integer.MAX_VALUE) d[i] = Math.min(d[i], d[i + 1]);
            }

            for (int i = 0; i < AL.length; i++) {
                if (d[AL[i]] < Integer.MAX_VALUE) d[BL[i]] = Math.min(d[BL[i]], d[AL[i]] + DL[i]);
            }

            for (int i = 0; i < BD.length; i++) {
                if (d[BD[i]] < Integer.MAX_VALUE) d[AD[i]] = Math.min(d[AD[i]], d[BD[i]] - DD[i]);
            }
        }

        if (d[0] < 0) {
            System.out.println(-1);
        } else {
            System.out.println((d[N - 1] == Integer.MAX_VALUE) ? -2 : d[N - 1]);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(), ML = in.nextInt(), MD = in.nextInt();

            int[] AL = new int[ML], BL = new int[ML], DL = new int[ML];
            int[] AD = new int[MD], BD = new int[MD], DD = new int[MD];

            for (int i = 0; i < ML; i++) {
                AL[i] = in.nextInt() - 1;
                BL[i] = in.nextInt() - 1;
                DL[i] = in.nextInt();
            }

            for (int i = 0; i < MD; i++) {
                AD[i] = in.nextInt() - 1;
                BD[i] = in.nextInt() - 1;
                DD[i] = in.nextInt();
            }

            solve(N, AL, BL, DL, AD, BD, DD);
        }
    }
}
